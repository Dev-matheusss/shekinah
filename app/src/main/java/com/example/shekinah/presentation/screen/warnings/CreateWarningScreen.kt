package com.example.shekinah.presentation.screen.warnings

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shekinah.R
import com.example.shekinah.components.ButtonComp
import com.example.shekinah.components.OutlineTextFieldComp
import com.example.shekinah.presentation.navigation.WarningsScreenRout
import com.example.shekinah.presentation.screen.warnings.viewmodel.state.WarningState
import rememberImagePicker

@Composable
fun CreateWarningRoute(
    state: WarningState,
    warningChange: (String) -> Unit,
    onClickSaveWarning: (warning: String, imageUri: Uri?) -> Unit,
    navigateTo: (Any) -> Unit
) {
    CreateWaningScreen(
        state = state,
        warningChange = warningChange,
        onClickSaveWarning = onClickSaveWarning,
        navigateTo = navigateTo
    )
}

@Composable
fun CreateWaningScreen(
    state: WarningState,
    warningChange: (String) -> Unit,
    onClickSaveWarning: (warning: String, imageUri: Uri?) -> Unit,
    navigateTo: (Any) -> Unit
) {
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val openGallery = rememberImagePicker { uri ->
        selectedImageUri.value = uri

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
                .background(color = Color.Black.copy(alpha = 0.5f))
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = "Criar Aviso.",
                    style = TextStyle(fontSize = 28.sp, color = Color.White)
                )

                Card(
                    modifier = Modifier
                        .size(200.dp)
                        .clickable { openGallery() },
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f))
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (selectedImageUri.value != null) {
                            AsyncImage(
                                model = selectedImageUri.value,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    Icons.Default.AccountCircle,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                                Text("Adicionar Foto", color = Color.White, fontSize = 12.sp)
                            }
                        }
                    }
                }
                OutlineTextFieldComp(
                    value = state.warning,
                    onValueChange = { newWarning ->
                        warningChange(newWarning)
                    },
                    maxLines = 34,
                    label = {
                        Text(
                            text = "Descreva o aviso",
                            style = TextStyle(color = Color.White)
                        )
                    },
                    textStyle = TextStyle(color = Color.White),
                )
                ButtonComp(
                    onClick = {
                        onClickSaveWarning(state.warning, selectedImageUri.value)
                        navigateTo(WarningsScreenRout)
                    }
                ) {
                    Text("Salvar Aviso")
                }
            }
        }
    }
}

@Composable
@Preview
fun CreateWarningPreview() {
    val fakeState = WarningState(
        warning = "teste"
    )

}