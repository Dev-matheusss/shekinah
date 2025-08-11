package com.example.shekinah.presentation.screen.listprayscreen

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shekinah.R


@Composable
fun ListPrayScreen(onClick: () -> Unit = {}) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable {}

    ) {
        }
    }


@Composable
fun NewItem(
    onClick: () -> Unit,

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(8.dp),

        ) {
        Row {
            Image(
                modifier = Modifier
                    .width(50.dp),
                painter = painterResource(R.drawable.background_app__1_),
                contentDescription = "perfil"
            )
            
            Column(
                modifier = Modifier
                    .padding(all = 6.dp)
            ) {
                Text(text = "")
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text =" pessoaPost.dataPost")

            }


        }

    }
}

@Composable
@Preview(showBackground = true)
fun ListPrayPreview() {

    ListPrayScreen()
}




