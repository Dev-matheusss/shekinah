package com.example.shekinah.data.model
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
@Parcelize
data class Pray (
    val id: String,
    val title: String ,
    val description: String
): Parcelable