package com.example.shekinah.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
data class Pray (
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val data: Long? = null
)