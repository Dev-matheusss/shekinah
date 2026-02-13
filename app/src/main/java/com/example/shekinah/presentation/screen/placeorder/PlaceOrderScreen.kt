package com.example.shekinah.presentation.screen.placeorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shekinah.R
import com.example.shekinah.components.ButtonComp
import com.example.shekinah.components.OutlineTextFieldComp
import com.example.shekinah.presentation.navigation.ListPrayScreenRout
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
        onClickSavePray = { title, description, name ->
            viewlModel.savePray(title, description, name )
        },
        navigateTo = navigateTo
    )
}

@Composable
fun PlaceOrderScreen(
    state: PlaceOrderState,
    titleChange: (String) -> Unit,
    descriptionChange: (String) -> Unit,
    onClickSavePray: (title: String, description: String, name: String) -> Unit,
    navigateTo: (Any) -> Unit
) {
    var errorMessage by remember { mutableStateOf("") }
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
                    text = stringResource(R.string.place_your_order_here),
                    style = TextStyle(fontSize = 28.sp, color = Color.White)
                )
                OutlineTextFieldComp(
                    value = state.title,
                    onValueChange = { newTitle ->
                        titleChange(newTitle)
                    },
                    maxLines = 1,
                    label = {
                        Text(
                            text = stringResource(R.string.name_input),
                            style = TextStyle(color = Color.White)
                        )
                    },
                    textStyle = TextStyle(color = Color.White),
                )
                OutlineTextFieldComp(
                    value = state.description,
                    onValueChange = { newDescription ->
                        descriptionChange(newDescription)
                    },
                    maxLines = 34,
                    label = {
                        Text(
                            text = stringResource(R.string.description_input),
                            style = TextStyle(color = Color.White)
                        )
                    },
                    textStyle = TextStyle(color = Color.White),
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                ButtonComp(
                    onClick = {
                        onClickSavePray(state.title, state.description, state.name)
                        if (state.title.isNotEmpty() && state.description.isNotEmpty()) {
                            navigateTo(ListPrayScreenRout)
                        } else {
                            errorMessage = "Preencha todos os campos"
                        }
                    }
                ) { Text(text = stringResource(R.string.save_pray)) }
            }
        }
    }
}

