package com.example.shekinah.presentation.screen.listprays

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shekinah.R
import com.example.shekinah.data.model.PrayDto
import com.example.shekinah.presentation.navigation.LoginScreenRout
import com.example.shekinah.presentation.navigation.PlaceOrderScreenRout
import com.example.shekinah.presentation.navigation.ProfileScreenRout
import com.example.shekinah.presentation.screen.listprays.viewModel.ListPrayViewModel
import com.example.shekinah.presentation.screen.listprays.viewModel.ListState
import org.koin.androidx.compose.koinViewModel


@Composable
fun ListRoute(
    onClickDetails: (PrayDto) -> Unit,
    navigateTo: (Any) -> Unit,
    state: ListState
) {
    ListPrayScreen(
        onClickDetails = onClickDetails,
        navigateTo = navigateTo,
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListPrayScreen(
    onClickDetails: (PrayDto) -> Unit,
    navigateTo: (Any) -> Unit,
    state: ListState,
) {
    val viewModel = koinViewModel<ListPrayViewModel>()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MyTopBar(
                onMenuOption = { option ->
                    when (option) {
                        "profile" -> {
                            navigateTo(ProfileScreenRout)
                        }
                        "config" -> {}
                        "logout" -> {
                            viewModel.logout()
                            navigateTo(LoginScreenRout)

                        }
                    }
                }
            )

        }) { innerPadding ->
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box() {
                Image(
                    painter = painterResource(R.drawable.background_app__1_),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxSize()
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxSize()
                ) {
                    items(
                        state.list,
                        key = { it.id },
                        itemContent = { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .clickable { onClickDetails(item) }
                                    .graphicsLayer { alpha = 0.99f }
                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(18.dp)
                                    ),
                                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.blue_mod)),
                                shape = RoundedCornerShape(18.dp),
                                elevation = CardDefaults.cardElevation(12.dp),

                                ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically

                                ) {
                                    AsyncImage(
                                        model = item.imageUrl, 
                                        contentDescription = item.title,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(64.dp)
                                            .clip(CircleShape)
                                            .border(1.dp, Color.White, CircleShape)
                                    )


                                    Column {
                                        Text(
                                            item.name,
                                            style = TextStyle(
                                                color = Color.White,
                                                fontSize = 22.sp
                                            ),
                                            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                                        )
                                        Text(
                                            item.title,
                                            style = TextStyle(
                                                color = Color.White,
                                                fontSize = 12.sp
                                            ),
                                            maxLines = 3,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.padding(
                                                start = 8.dp,
                                                top = 2.dp,
                                                end = 8.dp
                                            )
                                        )

                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(
                                            "Data Postagem ${formatDate(item.data)}",
                                            style = TextStyle(
                                                color = Color.White,
                                                fontSize = 10.sp
                                            ),
                                            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                                        )

                                    }

                                }
                            }
                        })
                }
            }
            FloatingActionButton(
                onClick = {
                    navigateTo(PlaceOrderScreenRout)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 16.dp),
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ) { Icon(Icons.Default.Add, contentDescription = "Adicionar") }
        }

    }

}

@Composable
@Preview
fun ListPrayPreview() {
    ListPrayScreen(
        onClickDetails = {}, navigateTo = {}, state = ListState(
            list = mutableListOf(
                PrayDto(id = "", title = "Pai Nosso", description = "Oração tradicional"),
                PrayDto(id = "", title = "Ave Maria", description = "Oração mariana")
            )
        )
    )
}

fun formatDate(millis: Long?): String {
    if (millis == null) return "Sem data"
    val date = java.util.Date(millis)
    val formatter = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return formatter.format(date)
}
