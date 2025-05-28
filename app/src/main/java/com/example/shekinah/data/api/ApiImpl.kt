package com.example.shekinah.data.api

import com.example.shekinah.data.api.model.AuthDto
import com.google.firebase.auth.FirebaseAuth
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
       }catch (e: Exception){
           AuthDto(isSucess = false, "${e.message}")
       }
    }
}