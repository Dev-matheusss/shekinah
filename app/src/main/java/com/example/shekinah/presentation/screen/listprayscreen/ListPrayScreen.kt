package com.example.shekinah.presentation.screen.listprayscreen

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shekinah.R
import com.example.shekinah.data.model.Pray
import com.example.shekinah.presentation.navigation.PlaceOrderRouts
import com.example.shekinah.presentation.screen.listprayscreen.viewModel.ListState
import org.koin.androidx.compose.koinViewModel


@Composable
fun ListRoute(
    onClickDetails: (Pray) -> Unit,
    navigateTo: (Any) -> Unit,
    state: ListState
) {
    ListPrayScreen(onClickDetails, navigateTo = navigateTo, state)
}

@Composable
fun ListPrayScreen(
    onClickDetails: (Pray) -> Unit,
    navigateTo: (Any) -> Unit,
    state: ListState,

    ) {

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Box() {
            Image(
                painter = painterResource(R.drawable.background_app__1_),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxSize()
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 35.dp)
                    .fillMaxSize()
            ) {
                items(
                    state.list,
                    itemContent = { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .clickable { onClickDetails(item)},
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(18.dp),
                            elevation = CardDefaults.cardElevation(12.dp),
                        ) {
                            Row() {
                                Image(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp)),
                                    painter = painterResource(R.drawable.pastor),
                                    contentDescription = "imagem")
                                Column {
                                    Text(
                                        item.title,
                                        style = TextStyle(color = Color.Black, fontSize = 18.sp),
                                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                                    )
                                    Text(
                                        item.description,
                                        style = TextStyle(color = Color.Black, fontSize = 12.sp),
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(start = 8.dp, top = 2.dp, end = 8.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        "Data Postagem : 16/01/2000",
                                        style = TextStyle(color = Color.Black, fontSize = 10.sp),
                                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                                    )

                                }

                            }
                        }
                    })
            }
        }
        FloatingActionButton(
            onClick = { navigateTo(PlaceOrderRouts) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            containerColor = Color.LightGray,
            contentColor = Color.Black
        ) { Icon(Icons.Default.Add, contentDescription = "Adicionar") }
    }
}

@Composable
@Preview
fun ListPrayPreview() {
    ListPrayScreen(
        onClickDetails = {}, navigateTo = {}, state = ListState(
            list = mutableListOf(
                Pray(id = "",title = "Pai Nosso", description = "Oração tradicional"),
                Pray(id = "",title = "Ave Maria", description = "Oração mariana")
            )
        )
    )
}
