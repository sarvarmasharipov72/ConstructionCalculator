package com.example.constructioncalculator.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
class Project(
    val id: Int = 0,
    val name: String,
    val price: Int,
    val date: String,
    val jobList: List<Jobs>
): Parcelable