package com.example.shekinah.data.datasource.fireauth

import android.net.Uri
import com.example.shekinah.data.api.ApiService
import com.example.shekinah.data.model.AuthDto
import com.example.shekinah.data.model.RecoverDto

class AuthDataSourceImpl(private val api: ApiService) : AuthDataSource {

    override suspend fun register(email: String, password: String, name: String, imageUri: Uri): AuthDto {
        return api.register(email, password, name, imageUri)
    }

    override suspend fun singIn(email: String, password: String): AuthDto {
        return api.singIn(email, password)
    }

    override suspend fun recoverPassword(email: String): RecoverDto {
        return api.recoverPassword(email)
    }
    override suspend fun logout(){
        api.logout()
    }


}