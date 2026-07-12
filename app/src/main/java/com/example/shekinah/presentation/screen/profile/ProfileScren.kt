package com.example.shekinah.presentation.screen.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.shekinah.components.ButtonComp
import com.example.shekinah.components.OutlineTextFieldComp
import com.example.shekinah.R
import com.example.shekinah.presentation.navigation.ListPrayScreenRout
import com.example.shekinah.presentation.screen.profile.viewmodel.ProfileState
import rememberImagePicker

@Composable
fun ProfileRout(
    navigateTo: (Any) -> Unit,
    state: ProfileState,
    onSaveProfile: (String, Uri?) -> Unit
) {
    ProfileScreen(
        navigateTo = navigateTo,
        state = state,
        onSaveProfile = onSaveProfile

    )
}

@Composable
fun ProfileScreen(
    navigateTo: (Any) -> Unit,
    state: ProfileState,
    onSaveProfile: (String, Uri?) -> Unit
) {
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val nameState = remember { mutableStateOf("") }
    val openGallery = rememberImagePicker { uri ->
        selectedImageUri.value = uri
    }

    LaunchedEffect(state) {
        if (state is ProfileState.Success) {
            nameState.value = state.name ?: ""
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
                    is ProfileState.Success -> {
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
                Text(
                    "Alterar imagem de perfil",
                    style = TextStyle(Color.White),
                    modifier = Modifier
                        .clickable {
                            openGallery()
                        }
                )
                OutlineTextFieldComp(
                    modifier = Modifier.fillMaxWidth(),
                    value = nameState.value,
                    onValueChange = { nameState.value = it },
                    label = {
                        Text(
                            "Nome",
                            style = TextStyle(Color.White)
                        )
                    },
                    textStyle = TextStyle(Color.White)
                )
                ButtonComp(
                    onClick = {
                        onSaveProfile(nameState.value, selectedImageUri.value)
                        navigateTo(ListPrayScreenRout)
                    },
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
    val fakeState = ProfileState.Success(
        name = "João Silva",
        photoUrl = "https://via.placeholder.com/150"
    )
    ProfileScreen(
        navigateTo = {},
        state = fakeState,
        onSaveProfile = { name, uri ->
        }
    )
}

