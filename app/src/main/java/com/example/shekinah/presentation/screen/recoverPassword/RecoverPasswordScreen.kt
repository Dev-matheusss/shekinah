package com.example.shekinah.presentation.screen.recoverPassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shekinah.R
@Composable
fun RecoverPasswordRoute(
    navigateTo: (Any) -> Unit,
    state: RecoverPasswordState,
    emailChange: (String) -> Unit,
    onClickRecover:(email: String)-> Unit

){
    RecoverPasswordScreen(
        navigateTo = navigateTo,
        state = state,
        emailChange = emailChange,
        onClickRecover = onClickRecover)

}

@Composable
fun RecoverPasswordScreen(
    navigateTo: (Any) -> Unit,
    state: RecoverPasswordState,
    emailChange:(String)-> Unit,
    onClickRecover: (email: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.background_app__1_),
            contentDescription = "backgound",
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
                verticalArrangement = spacedBy(8.dp, Alignment.CenterVertically)
            ) {
                Text(text = "Recuperar Senha.", fontSize = 32.sp, color = Color.White)
                OutlinedTextField(
                    value = state.email,
                    onValueChange = {newEmail ->
                        emailChange (newEmail)
                    },
                    label = {
                        Text(text = "Email de recuperação:", color = Color.White)
                    },
                    textStyle = TextStyle(Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )
                state.message.let {
                    Text(text = it,
                        color = Color.Red)
                }
                Button(
                    onClick = {onClickRecover(state.email)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent.copy(alpha = 0.8f))
                ) {
                    Text(text = "Enviar")
                }
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun RecoverPasswordPreview() {
}
