package com.example.shekinah.data.api.firestore

import com.example.shekinah.data.model.Pray
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class FireStoreImpl(private val fireStore: FirebaseFirestore) : FireStore {
    val _allPrays = MutableStateFlow<MutableList<Pray>>(mutableListOf())
    val allPrays: StateFlow<MutableList<Pray>> = _allPrays

    override suspend fun savePray(title: String, description: String,  ) {
        val colletion = fireStore.collection("prays")
        val docRef = colletion.document()
        val prayMap = hashMapOf(
            "id" to docRef.id,
            "title" to title,
            "description" to description,

        )
        fireStore.collection("prays").add(prayMap).await()
    }

    override suspend fun recoverPray(): Flow<MutableList<Pray>> {
        val listPray: MutableList<Pray> = mutableListOf()
        val result = fireStore.collection("prays").get().await()
        for (document in result){
            val pray = document.toObject(Pray::class.java)
            if(pray != null){
                listPray.add(pray)
            }
        }
        _allPrays.value = listPray

        return allPrays
    }
}