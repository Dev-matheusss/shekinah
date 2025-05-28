package com.example.shekinah.presentation.screen.listprayscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shekinah.R
import com.example.shekinah.data.PessoaPost
import com.example.shekinah.data.ProviderListData


@Composable
fun ListPrayScreen(onClick: () -> Unit = {}, list: List<PessoaPost>) {
    val list = ProviderListData().listaDePosts
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(items = list, itemContent = { item ->
            NewItem(onClick, item)
        })
    }
}

@Composable
fun NewItem(
    onClick: () -> Unit,
    pessoaPost: PessoaPost
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(8.dp),

        ) {
        Row {
            Image(
                painter = painterResource(R.drawable.background_app__1_),
                contentDescription = "perfil"
            )
            Spacer(modifier = Modifier.padding(5.dp))

            Column(
                modifier = Modifier
                    .padding(all = 6.dp)
            ) {
                Text(text = pessoaPost.name)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = pessoaPost.dataPost)
                Spacer(modifier = Modifier.padding(10.dp))

            }

            Spacer(modifier = Modifier.padding(65.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .background(color = Color.Transparent),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.8f))

            ) {
                Text(text = "Ler")
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun ListPrayPreview() {
    val mockList = listOf(
        PessoaPost("João", "01/01/2025"),
        PessoaPost("Maria", "02/02/2025")
    )
    ListPrayScreen(onClick = {}, list = mockList)
}




