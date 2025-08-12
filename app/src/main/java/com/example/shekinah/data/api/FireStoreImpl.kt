package com.example.shekinah.data.api

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FireStoreImpl(private val fireStore: FirebaseFirestore ): FireStore {
    override suspend fun savePray(title: String, description: String){
        val prayMap = hashMapOf(
            "Title" to title,
            "Description" to description)
        fireStore.collection("pray").document(title).set(prayMap).await()
    }
}