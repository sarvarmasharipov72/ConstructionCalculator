package com.example.constructioncalculator.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Material(
    val id: Int,
    val name: String,
    val measurement: String,
    val price: Int,
    var percent: Byte = 0
    ): Parcelable