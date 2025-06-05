package com.example.shekinah.data

class ProviderListData {
    val listaDePosts = List(100) { index ->
        PessoaPost(
            name = "matheus silva de souza $index",
            dataPost = "2025-03-${(index % 30 + 1).toString().padStart(2, '0')}"
        )
    }
}
