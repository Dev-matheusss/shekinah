package com.example.shekinah.presentation.screen.praydetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shekinah.R

@Composable
fun PrayRoute(
    id: String,
    state: ScreenDetailsState
) {
    PrayDetailsScreen(id = id, state = state)
}

@Composable
fun PrayDetailsScreen(
    id: String,
    state: ScreenDetailsState
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.background_app__1_),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
        )
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 42.dp, horizontal = 12.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(18.dp)),
        shape = RoundedCornerShape(size = 18.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.blue_mod)
        )

    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = state.pray?.title ?: "...",
                color = Color.White,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp)
            )

            Text(
                text = state.pray?.description ?: "...",
                color = Color.White,
                fontSize = 22.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PrayDetailsPreview() {
}
