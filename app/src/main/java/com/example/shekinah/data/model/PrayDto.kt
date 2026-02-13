package com.example.shekinah.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PrayDto(
    val name: String = "",
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String? = null,
    val data: Long? = null
)