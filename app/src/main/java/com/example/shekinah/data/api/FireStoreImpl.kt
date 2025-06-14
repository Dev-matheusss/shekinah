package com.example.shekinah.data.api

import com.google.firebase.firestore.FirebaseFirestore

class FireStoreImpl {
    val db = FirebaseFirestore.getInstance()
    fun savePray(title: String, description: String){
        val prayMap = hashMapOf(
            "Title" to title,
            "Description" to description
        )
        db.collection("prays").document(title).set(prayMap)
    }

}