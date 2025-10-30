package com.example.shekinah.data.api.fireauth

import android.util.Log
import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.RecoverDto
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ApiImpl(val auth: FirebaseAuth) : ApiService {

    override suspend fun register(email: String, password: String): AuthDto {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            AuthDto(isSucess = true, "")
        } catch (e: Exception) {
            AuthDto(isSucess = false, "${e.message}")
        }
    }

    override suspend fun singIn(email: String, password: String): AuthDto {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            AuthDto(isSucess = true, "")
        } catch (e: Exception) {
            AuthDto(isSucess = false, "${e.message}")
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
}

