package com.example.shekinah.presentation.screen.warnings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.shekinah.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shekinah.presentation.navigation.CreateWarningScreenRout
import com.example.shekinah.presentation.screen.warnings.viewmodel.state.ListWarningsState

@Composable
fun WarningsRoute(
    state: ListWarningsState,
    navigateTo: (Any) -> Unit
){
    WarningScreen(
        state = state,
        navigateTo = navigateTo

    )
}
@Composable
fun WarningScreen(
    state: ListWarningsState,
    navigateTo: (Any) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(R.drawable.background_app__1_), contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize()

        ) {
            items(
                state.list,
                itemContent = { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .clickable { }
                            .graphicsLayer { alpha = 0.99f }
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(18.dp)
                            ),
                        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.blue_mod)),
                        shape = RoundedCornerShape(18.dp),
                        elevation = CardDefaults.cardElevation(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = item.imageUrl,
                                placeholder = painterResource(R.drawable.nao),
                                error = painterResource(R.drawable.background_app__1_),
                                contentDescription = "imagem do aviso",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .border(1.dp, Color.White, CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                            Text(item.warning, style = TextStyle(Color.White))
                        }
                    }
                }

            )

        }
        FloatingActionButton(
            onClick = {
                navigateTo(CreateWarningScreenRout)
            },
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
fun WarningScreenPreview() {
}