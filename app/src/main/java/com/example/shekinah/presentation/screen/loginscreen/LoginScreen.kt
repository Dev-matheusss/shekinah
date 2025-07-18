package com.example.shekinah.presentation.screen.loginscreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shekinah.R
import com.example.shekinah.domain.model.Auth
import com.example.shekinah.presentation.navigation.CreateAcountRouts
import com.example.shekinah.presentation.navigation.ListPrayRouts
import com.example.shekinah.presentation.screen.loginscreen.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel


data class LoginState(
    val email: String,
    val password: String,
    val result: Auth? = null
)

@Composable
fun LoginRoute(
    navigateTo: (Any) -> Unit
) {
    val viewModel = koinViewModel<LoginViewModel>()
    val state = viewModel.loginState.collectAsState().value

    if (state.result?.isSuccsess == true) {
        navigateTo(ListPrayRouts)
    }

    LoginScreen(
        state = state,
        emailChange = {
            viewModel.emailChange(it)
        },
        passwordChange = {
            viewModel.passwordChange(it)
        },
        onClickSingIn = { email, password ->
            viewModel.singIn(email, password)
        },
        navigateTo = navigateTo
    )

}

@Composable
fun LoginScreen(
    state: LoginState,
    emailChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    onClickSingIn: (email: String, password: String) -> Unit,
    navigateTo: (Any) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.background_app__1_),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
                .clickable {}
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Shekinah Orações",
            style = TextStyle(fontSize = 28.sp, color = Color.White)
        )
        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = { newPassword ->
                emailChange(newPassword)
            },
            label = { Text(text = "email", style = TextStyle(color = Color.White)) },
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { newPassword ->
                passwordChange(newPassword)
            },
            label = { Text(text = "senha", style = TextStyle(color = Color.White)) },
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        Button(
            onClick = { onClickSingIn(state.email, state.password) },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.8f))
        ) { Text(text = "Entrar") }

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        Text(text = "Não tem uma conta?", style = TextStyle(color = Color.White))

        Text(

            text = "Cadastre-se",
            color = Color.White,
            modifier = Modifier
                .clickable { navigateTo(CreateAcountRouts)}
                .padding(top = 5.dp)

        )
    }


}


@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        state = LoginState("matheus@gmail.com", "senha123"),
        emailChange = {},
        passwordChange = {},
        onClickSingIn = { _, _ -> },
        navigateTo = {}
    )

}