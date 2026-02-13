package com.example.shekinah.presentation.screen.register

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.shekinah.R
import com.example.shekinah.components.ButtonComp
import com.example.shekinah.components.OutlineTextFieldComp
import com.example.shekinah.presentation.navigation.ListPrayScreenRout
import com.example.shekinah.presentation.screen.profile.ProfileImageState
import com.example.shekinah.presentation.screen.register.viewmodel.RegisterState
import com.example.shekinah.presentation.screen.register.viewmodel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel
import rememberImagePicker

@Composable
fun RegisterRoute(
    navigateTo: (Any) -> Unit
) {
    val viewModel = koinViewModel<RegisterViewModel>()
    val state by viewModel.registerState.collectAsState()
    if (state.result?.isSuccsess == true) {
        navigateTo(ListPrayScreenRout)
    }
    val openGallery = rememberImagePicker { uri ->
        viewModel.onImageSelected(uri)
    }
    CreateAcountScreen(
        state = state,
        openGallery = openGallery,
        nameChange = {
            viewModel.nameChange(it)
        },
        emailChange = {
            viewModel.emailChange(it)
        },
        passwordChange = {
            viewModel.passwordChange(it)
        },
        onClickRegister = { email, password, name, imageUri->
            viewModel.register(email,password,name,imageUri)
        },
        navigateTo = navigateTo,
    )
}

@Composable
fun CreateAcountScreen(
    state: RegisterState,
    openGallery: () -> Unit,
    nameChange: (String) -> Unit,
    emailChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    onClickRegister: (email: String,password: String, name: String, imageUri: Uri) -> Unit,
    navigateTo: (Any) -> Unit,

    ) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.background_app__1_),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize()

        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable {}

        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = stringResource(R.string.be_one_of_us),
            style = TextStyle(fontSize = 28.sp, color = Color.White)
        )
        Box() {
            when (state.imageState) {
                is ProfileImageState.Success -> {
                    val imageUrl = (state.imageState as ProfileImageState.Success).photoUrl
                    Log.d("RegisterImage", "Imagem SUCCESS: $imageUrl")
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = "Imagem de perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                }
                is ProfileImageState.Error -> {

                    val error = (state.imageState as ProfileImageState.Error).message
                    Log.e("RegisterImage", "Erro imagem: $error")

                    Image(
                        painter = painterResource(R.drawable.nao),
                        contentDescription = "Imagem de perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                }

                else -> {
                    Image(
                        painter = painterResource(R.drawable.nao),
                        contentDescription = "Imagem de perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                }
            }
            IconButton(
                onClick = { openGallery() },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edit",
                    tint = Color.White,

                    )

            }
        }

        OutlineTextFieldComp(
            value = state.name, onValueChange = { newName ->
                nameChange(newName)
            }, label = {
                Text(
                    text = stringResource(R.string.name_input),
                    style = TextStyle(color = Color.White)
                )
            }, textStyle = TextStyle(Color.White)
        )

        OutlineTextFieldComp(
            value = state.email, onValueChange = { newEmail ->
                emailChange(newEmail)
            }, label = {
                Text(
                    text = stringResource(R.string.email_input),
                    style = TextStyle(color = Color.White)
                )
            }, textStyle = TextStyle(Color.White)
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
                            if (passwordVisible) R.drawable.outline_password_sample
                            else R.drawable.outline_password_hidden
                        ),
                        contentDescription = if (passwordVisible) "Esconder senha" else "Mostrar senha",
                        tint = Color.White
                    )
                }
            },
            textStyle = TextStyle(Color.White),
        )

        state.result?.message?.let {
            Text(
                text = it, color = Color.Red, fontSize = 16.sp, modifier = Modifier.padding(14.dp)
            )
        }
        ButtonComp(
            onClick = {
                state.imageUri?.let { uri ->
                    onClickRegister(state.email, state.password, state.name, uri)
                }
            }
        ) { Text(text = stringResource(R.string.enter_button)) }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp), thickness = 2.dp, color = Color.White
        )
        Button(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .background(color = Color.Transparent),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.8f)),
            onClick = {}) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    modifier = Modifier.size(26.dp),
                    contentDescription = "image google"
                )

                Text(text = stringResource(R.string.continue_with_google))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CreateAcountScreenPreview() {
    CreateAcountScreen(
        state = RegisterState(
            name = "Matheus",
            email = "matheus@gmail.com",
            password = "123456",
            imageState = ProfileImageState.Idle
        ),
        nameChange = {},
        emailChange = {},
        passwordChange = {},
        onClickRegister = { _, _, _, _ -> },
        navigateTo = {},
        openGallery = {}
    )
}
