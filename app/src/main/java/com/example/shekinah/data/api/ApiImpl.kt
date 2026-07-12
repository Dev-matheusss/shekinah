package com.example.shekinah.data.api

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.example.shekinah.data.m.UserDto
import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.data.model.RecoverDto
import com.example.shekinah.data.model.WarningDto
import com.example.shekinah.presentation.screen.ui.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.auth.User
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ApiImpl(
    val auth: FirebaseAuth,
    val fireStore: FirebaseFirestore,
    val storage: FirebaseStorage
) : ApiService {

    //funções de update.
    override suspend fun updateProfile(newName: String, newUri: Uri?) {
        try {
            val user = auth.currentUser ?: return
            val uid = user.uid
            val finalPhotoUrl: String? = newUri?.let { uploadProfileImage(it) }
            val profileUpdates = userProfileChangeRequest {
                displayName = newName
                if (finalPhotoUrl != null) {
                    photoUri = finalPhotoUrl.toUri()
                }
            }
            user.updateProfile(profileUpdates).await()
            val userRef = fireStore.collection("users").document(uid)
            val updates = mutableMapOf<String, Any>("name" to newName)
            if (finalPhotoUrl != null) {
                updates["photoUrl"] = finalPhotoUrl
            }

            userRef.update(updates).await()

        } catch (e: Exception) {
            throw e
        }
    }

    //funções de autenticação.
    override suspend fun singIn(email: String, password: String): AuthDto {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            AuthDto(isSucess = true, "")
        } catch (e: Exception) {
            AuthDto(isSucess = false, "${e.message}")
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override suspend fun register(
        email: String,
        password: String,
        name: String,
        imageUri: Uri?
    ): AuthDto {

        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user ?: return AuthDto(false, "Usuário nulo")

            var imageUrl: String? = null

            if (imageUri != null) {
                imageUrl = uploadProfileImage(imageUri)
            }

            val profile = userProfileChangeRequest {
                displayName = name
                photoUri = imageUrl?.toUri()
            }

            user.updateProfile(profile).await()

            val userMap = hashMapOf(
                "uid" to user.uid,
                "name" to name,
                "email" to email,
                "photoUrl" to imageUrl
            )

            fireStore.collection("users")
                .document(user.uid)
                .set(userMap)
                .await()

            AuthDto(isSucess = true, message = "")

        } catch (e: Exception) {
            AuthDto(isSucess = false, message = e.message ?: "Erro")
        }
    }

    //funções de save.

    override suspend fun uploadProfileImage(image: Uri): String? {
        val user = FirebaseAuth.getInstance().currentUser ?: return null
        val storageRef = storage.reference
            .child("profileImages/${user.uid}/profile.jpg")
        return try {
            storageRef.putFile(image).await()
            val downloadUrl = storageRef.downloadUrl.await().toString()
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(downloadUrl))
                .build()
            user.updateProfile(profileUpdates).await()
            downloadUrl
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

    override suspend fun savePray(title: String, description: String, name: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val userName = user?.displayName ?: "Usuário"
        val userPhoto = user?.photoUrl?.toString()
        val colletion = fireStore.collection("prays")
        val docRef = colletion.document()
        val prayMap = hashMapOf(
            "name" to userName,
            "id" to docRef.id,
            "title" to title,
            "description" to description,
            "imageUrl" to userPhoto,
            "data" to System.currentTimeMillis()

        )
        fireStore.collection("prays").add(prayMap).await()
    }

    override suspend fun saveWarning(warning: String, imageUri: Uri?) {
        try {
            val collection = fireStore.collection("warnings")
            val docRef = collection.document()
            val id = docRef.id

            val warningMap = hashMapOf(
                "warning" to warning,
                "imageUrl" to null,
                "id" to id,
                "data" to System.currentTimeMillis()
            )
            docRef.set(warningMap).await()

            imageUri?.let { uri ->
                val storageRef = storage.reference.child("warningsImage/$id.jpg")
                storageRef.putFile(uri).await()
                val imageUrl = storageRef.downloadUrl.await().toString()
                docRef.update("imageUrl", imageUrl).await()
            }
        } catch (e: Exception) {
            Log.e("ApiImpl", "Erro ao salvar aviso", e)
            throw e
        }
    }

    //funções de recuperação.
    override suspend fun getCurrentId(): String? {
        return auth.currentUser?.uid
    }

    override suspend fun recoverWarnings(): Flow<MutableList<WarningDto>> = callbackFlow {
        val listener = fireStore.collection("warnings")
            .orderBy("data", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val listWarnings = mutableListOf<WarningDto>()

                value?.forEach { document ->
                    val warning = document.toObject(WarningDto::class.java)
                    listWarnings.add(warning)
                }
                trySend(listWarnings)
            }
        awaitClose {
            listener.remove()
        }
    }

    override suspend fun recoverPray(): Flow<MutableList<PrayDto>> {
        return flow {
            val listPray: MutableList<PrayDto> = mutableListOf()
            val result =
                fireStore.collection("prays").orderBy("data", Query.Direction.DESCENDING).get()
                    .await()
            for (document in result) {
                val pray = document.toObject(PrayDto::class.java)
                listPray.add(pray)
            }

            emit(listPray)
        }
    }

    override suspend fun recoverDetails(id: String): PrayDto? {
        return try {
            val snapshot = fireStore.collection("prays").whereEqualTo("id", id).get().await()
            snapshot.documents.firstOrNull()?.toObject(PrayDto::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun recoverPassword(email: String): RecoverDto {
        return try {
            val result = auth.fetchSignInMethodsForEmail(email).await()
            val methods = result.signInMethods
            if (!methods.isNullOrEmpty()) {
                auth.sendPasswordResetEmail(email).await()
                RecoverDto(isSuccess = true, message = "")
            } else {
                RecoverDto(isSuccess = false, message = "")
            }
        } catch (e: Exception) {
            RecoverDto(isSuccess = false, message = "${e.message}")

        }
    }

    override suspend fun fetchUserProfile(userId: String): UserDto {
        return try {
            val document = fireStore.collection("users").document(userId).get().await()
            UserDto(
                name = document.getString("name") ?: "",
                email = document.getString("email") ?: "",
                photoUrl = document.getString("photoUrl") ?: ""
            )
        } catch (e: Exception) {
            UserDto(errorMessage = e.message ?: "Erro ao buscar perfil")
        }
    }


}

