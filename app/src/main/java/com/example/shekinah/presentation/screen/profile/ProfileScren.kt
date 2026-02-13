package com.example.shekinah.presentation.screen.profile

import android.net.Uri
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.shekinah.components.ButtonComp
import com.example.shekinah.components.OutlineTextFieldComp
import com.example.shekinah.R
import rememberImagePicker

@Composable
fun ProfileRout(
    navigateTo: (Any) -> Unit,
    state: ProfileImageState,
    onUploadImage: (Uri) -> Unit
) {
    ProfileScreen(
        navigateTo = navigateTo,
        state = state,
        onUploadImage = onUploadImage

    )
}

@Composable
fun ProfileScreen(
    navigateTo: (Any) -> Unit,
    state: ProfileImageState,
    onUploadImage: (Uri) -> Unit
) {
 val openGallery = rememberImagePicker {uri ->
     onUploadImage(uri)
 }
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }

    LaunchedEffect(state) {
        if (state is ProfileImageState.Success) {
            nameState.value = state.name ?: ""
            emailState.value = state.email ?: ""
        }
    }


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
                    .padding()
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                when (state) {
                    is ProfileImageState.Success -> {
                        Image(
                            painter = rememberAsyncImagePainter(model = state.photoUrl),
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
                Text("Alterar imagem de perfil",
                    style = TextStyle(Color.White),
                    modifier = Modifier
                        .clickable{
                            openGallery()
                        }
                )


                OutlineTextFieldComp(
                    modifier = Modifier.fillMaxWidth(),
                    value = nameState.value,
                    onValueChange = {nameState.value = it},
                    label = {
                        Text(
                            "Nome",
                            style = TextStyle(Color.White)
                        )
                    },
                    textStyle = TextStyle(Color.White)
                )

                OutlineTextFieldComp(
                    modifier = Modifier.fillMaxWidth(),
                    value = emailState.value,
                    onValueChange = {emailState.value = it},
                    label = { Text("E-mail",
                        style = TextStyle(Color.White)) },
                    textStyle = TextStyle(Color.White),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )

                ButtonComp(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar")
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {

    val fakeState = ProfileImageState.Success(name = "", email = "",
        photoUrl = "https://via.placeholder.com/150"
    )

    ProfileScreen(
        navigateTo = {},
        state = fakeState,
        onUploadImage = {}
    )
}

