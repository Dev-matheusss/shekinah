package com.example.shekinah.data.api.firestore

import com.google.firebase.firestore.FirebaseFirestore

class FireStoreImpl(private val fireStore: FirebaseFirestore ): FireStore {
    override suspend fun savePray(title: String, description: String){
        val prayMap = hashMapOf(
            "Title" to title,
            "Description" to description)
        fireStore.collection("prays").document(title).set(prayMap)
    }
}