package com.example.shekinah.presentation.screen.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shekinah.R
import com.example.shekinah.components.ButtonComp
import com.example.shekinah.components.OutlineTextFieldComp
import com.example.shekinah.presentation.navigation.RegisterScreenRout
import com.example.shekinah.presentation.navigation.ListPrayScreenRout
import com.example.shekinah.presentation.navigation.RecoverPassworScreendRout
import com.example.shekinah.presentation.screen.login.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginRoute(
    navigateTo: (Any) -> Unit
) {
    val viewModel = koinViewModel<LoginViewModel>()
    val state = viewModel.loginState.collectAsState().value

    if (state.result?.isSuccsess == true) {
        navigateTo(ListPrayScreenRout)
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
            .background(Color.Black)

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
                .background(color = Color.Black.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(R.string.shekinah_orações),
                    style = TextStyle(fontSize = 28.sp, color = Color.White, fontWeight = FontWeight.Bold)
                )

                OutlineTextFieldComp(
                    value = state.email,
                    onValueChange = { newEmail ->
                        emailChange(newEmail)
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.email_input),
                            style = TextStyle(color = Color.White)
                        )
                    },
                    textStyle = TextStyle(color = Color.White)
                )
                var passwordVisible by remember { mutableStateOf(false) }
                OutlineTextFieldComp(
                    value = state.password,
                    onValueChange = { newPassword ->
                        passwordChange(newPassword)
                    },
                    visualTransformation = if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.password_input),
                            style = TextStyle(color = Color.White)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(
                                    if (passwordVisible)
                                        R.drawable.outline_password_sample
                                    else
                                        R.drawable.outline_password_hidden
                                ),
                                contentDescription = if (passwordVisible) "Esconder senha" else "Mostrar senha",
                                tint = Color.White
                            )
                        }
                    },
                    textStyle = TextStyle(color = Color.White),
                )


                Text(
                    text = "Esqueceu sua senha?",
                    style = TextStyle(Color.White),
                    modifier = Modifier
                        .clickable { navigateTo(RecoverPassworScreendRout) }
                        .padding(end = 12.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                )


                state.result?.message?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 14.dp)
                    )
                }
                ButtonComp(
                    onClick = {onClickSingIn(state.email, state.password)}
                ) {
                    Text(text = stringResource(R.string.enter_button))
                }

                Text(
                    text = stringResource(R.string.dont_have_an_account),
                    style = TextStyle(color = Color.White)
                )
                Text(
                    text = stringResource(R.string.register),
                    color = colorResource(R.color.orange_mod),
                    modifier = Modifier
                        .clickable { navigateTo(RegisterScreenRout) }
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        state = LoginState("matheus@gmail.com", "senha123"),
        emailChange = {},
        passwordChange = {},
        onClickSingIn = { _, _ -> },
        navigateTo = {}
    )
}