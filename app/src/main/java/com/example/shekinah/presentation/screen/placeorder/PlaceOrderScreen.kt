package com.example.shekinah.presentation.screen.placeorder

import android.widget.Toast
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shekinah.R
import com.example.shekinah.components.OutlineTextFieldComp
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
                    label = {
                        Text(
                            text = stringResource(R.string.tilte_input),
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
                Button(
                    onClick = {
                        onClickSavePray(state.title, state.description)
                        if (state.title.isNotEmpty() && state.description.isNotEmpty()) {
                            navigateTo(ListPrayRouts)
                        } else {
                            errorMessage = "Preencha todos os campos"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .background(color = Color.Transparent),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.8f))
                ) {
                    Text(text = stringResource(R.string.save_pray))
                }
            }
        }
    }
}

