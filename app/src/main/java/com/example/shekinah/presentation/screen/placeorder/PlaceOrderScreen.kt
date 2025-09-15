package com.example.shekinah.presentation.screen.placeorder

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
import androidx.compose.material3.ModalDrawerSheet
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
import com.example.shekinah.data.model.Pray
import com.example.shekinah.presentation.navigation.ListPrayRouts
import com.example.shekinah.presentation.screen.listprayscreen.viewModel.ListState
import com.example.shekinah.presentation.screen.placeorder.PlaceOrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaceOrderRout(
    navigateTo: (Any) -> Unit
) {
    val viewlModel = koinViewModel<PlaceOrderViewModel>()
    val state = viewlModel.placeOrderState.collectAsState().value

    PlaceOrderScreen(
        state = state,
        titleChange = {
            viewlModel.titleChange(it)
        },
        descriptionChange = {
            viewlModel.descriptionChange(it)
        },
        onClickSavePray = { title, description ->
            viewlModel.savePray(title, description)
        },
        navigateTo = navigateTo
    )
}

@Composable
fun PlaceOrderScreen(
    state: PlaceOrderState,
    titleChange: (String) -> Unit,
    descriptionChange: (String) -> Unit,
    onClickSavePray: (title: String, description: String) -> Unit,
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
                .background(color = Color.Black.copy(alpha = 0.5f))
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Faça seu pedido aqui.",
                    style = TextStyle(fontSize = 28.sp, color = Color.White)
                )
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = state.title,
                    onValueChange = { newTitle ->
                        titleChange(newTitle)
                    },
                    label = { Text(text = "Título", style = TextStyle(color = Color.White)) },
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = state.description,
                    onValueChange = { newDescription ->
                        descriptionChange(newDescription)
                    },
                    label = { Text(text = "Descrição", style = TextStyle(color = Color.White)) },
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        onClickSavePray(state.title, state.description)
                        if (state.title.isNotEmpty()) {
                            navigateTo(ListPrayRouts)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.8f))
                ) {
                    Text(text = "salvar oração")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PlaceOrderPreview() {
}