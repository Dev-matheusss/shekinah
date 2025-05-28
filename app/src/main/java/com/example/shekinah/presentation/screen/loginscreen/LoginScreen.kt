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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shekinah.R

@Composable
fun LoginScreen(
    onClick: () -> Unit,
    onClickCreateAcount: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

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
            text = "Shekinah Orações",
            style = TextStyle(fontSize = 28.sp, color = Color.White)
        )

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {},
            label = { Text(text = "email", style = TextStyle(color = Color.White)) },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = {},
            label = { Text(text = "senha", style = TextStyle(color = Color.White)) },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(bottom = 8.dp))

        Button(
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.8f))
        ) { Text(text = "Entrar") }

        Spacer(modifier = Modifier.padding(bottom = 8.dp))


        Text(text = "Não tem uma conta?", style = TextStyle(color = Color.White))
        Button(
            onClick = { onClickCreateAcount() },
            modifier = Modifier
                .background(color = Color.Transparent),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(text = "Cadastre-se.")
        }
    }


}


@Composable
@Preview
fun LoginScreenPreview() {

}