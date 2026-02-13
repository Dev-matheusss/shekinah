package com.example.shekinah.domain.usecase.firebaseauth

import android.net.Uri
import com.example.shekinah.data.model.RecoverDto
import com.example.shekinah.data.repository.firebaseauth.AuthRepository
import com.example.shekinah.domain.model.Auth

class AuthUseCaseImpl(private val repository: AuthRepository) : AuthUseCase {
    override suspend fun logout(){
         repository.logout()
    }

    override fun getCurrentUserName(): String? {
        return repository.getCurrentUserName()
    }

    override suspend fun register(email: String, password: String,name: String, imageUri: Uri): Auth {
        val result = repository.register(email, password, name, imageUri)
        return if (result.isSucess) {
            Auth(isSuccsess = true, message = "Bem vindo")
        } else {
            when (result.message) {
                "Given String is empty or null" -> {
                    Auth(
                        message = "Preencha todos os campos",
                    )
                }

                "The email address is badly formatted." -> {
                    Auth(
                        message = "Email não é válido",
                    )
                }

                "The supplied auth credential is incorrect, malformed or has expired." -> {
                    Auth(
                        message = "Email ou senha incorreto",
                    )
                }

                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> {
                    Auth(
                        message = "Vefique se possui conexão com a internet",
                    )
                }

                else -> Auth(
                    message = "Erro genérico",
                )
            }
        }
    }


    override suspend fun singIn(email: String, password: String): Auth {
        val result = repository.singIn(email, password)
        return if (result.isSucess) {
            Auth(isSuccsess = true, message = "bem vindo")
        } else {
            when (result.message) {
                "Given String is empty or null" -> {
                    Auth(message = "Preencha todos campos")
                }

                "The email address is badly formatted." -> {
                    Auth(message = "Email não é válido")
                }

                "The supplied auth credential is incorrect, malformed or has expired." -> {
                    Auth(message = "Email ou senha incorreto")
                }

                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> {
                    Auth(message = "Vefique se possui conexão com a internet")
                }

                else -> Auth(message = "Erro genérico")
            }
        }
    }

    override suspend fun recoverPassword(email: String): RecoverDto {
        val result = repository.recoverPassword(email)
        return if (result.isSuccess){
            RecoverDto(isSuccess = true, message = "Email enviado, verifique sua caixa de entrada.")
        }else{
            when (result.message) {
                "Given String is empty or null" -> {
                    RecoverDto(message = "Preencha o campo de email")
                }

                "The email address is badly formatted." -> {
                    RecoverDto(message = "Email não é válido")
                }

                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> {
                    RecoverDto(message = "Vefique se possui conexão com a internet")
                }

                else -> RecoverDto(message = "Erro genérico")
            }
        }
}

}





