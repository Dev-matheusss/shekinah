package com.example.shekinah.presentation.screen.registerscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.shekinah.presentation.navigation.ListPrayRouts
import com.example.shekinah.presentation.screen.registerscreen.CreateAcountScreen
import com.example.shekinah.presentation.screen.registerscreen.viewmodel.RegisterState
import com.example.shekinah.presentation.screen.registerscreen.viewmodel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterRoute(
    navigateTo: (Any) -> Unit
) {
    val viewModel = koinViewModel<RegisterViewModel>()
    val state = viewModel.registerState.collectAsState().value
    if (state.result?.isSuccsess == true) {
        navigateTo(ListPrayRouts)
    }
    CreateAcountScreen(
        state = state,
        nameChange = {
            viewModel.nameChange(it)
        },
        emailChange = {
            viewModel.emailChange(it)
        },
        passwordChange = {
            viewModel.passwordChange(it)
        },
        onClickRegister = {email, password ->
            viewModel.register(email, password)
        },
        navigateTo = navigateTo
    )
}

@Composable
fun CreateAcountScreen(
    state: RegisterState,
    nameChange: (String) -> Unit,
    emailChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    onClickRegister:(email: String, password: String)-> Unit,
    navigateTo: (Any) -> Unit,

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
                .background(Color.Black.copy(alpha = 0.5f))
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
            text = "Seja um de nós",
            style = TextStyle(fontSize = 28.sp, color = Color.White)
        )
        OutlinedTextField(
            value = state.name,
            onValueChange = {newName->
                nameChange(newName)
            },
            label = { Text(text = "Nome", style = TextStyle(color = Color.White)) },
            textStyle = TextStyle(Color.White),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = {newEmail->
                emailChange(newEmail)
            },
            label = { Text(text = "email", style = TextStyle(color = Color.White)) },
            textStyle = TextStyle(Color.White),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = {newPassword->
                passwordChange(newPassword)
            },
            label = { Text(text = "Senha", style = TextStyle(color = Color.White)) },
            textStyle = TextStyle(Color.White),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        Button(
            onClick = { onClickRegister(state.email, state.password) },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.8f))
        ) { Text(text = "Entrar") }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 3.dp,
            color = Color.White
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            onClick = {}
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    modifier = Modifier.size(36.dp),
                    contentDescription = "image google"
                )

                Spacer(modifier = Modifier.width(22.dp))

                Text(text = "Continue com o google")
            }
        }
    }
}

@Composable
@Preview
fun CreateAcountScreenPreview() {
    CreateAcountScreen(
        state = RegisterState("matheus", "matheus@gmail.com", "senha123"),
        nameChange = {},
        emailChange = {},
        passwordChange = {},
        onClickRegister = { _, _ -> },
        navigateTo = {}
    )
}