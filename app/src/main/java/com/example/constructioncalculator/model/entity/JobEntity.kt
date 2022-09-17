package com.example.constructioncalculator.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.constructioncalculator.model.Material

@Entity(tableName = "jobs")
class JobEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val measurement: String,
    val price: Int,
    val materialList: List<Material>,
    val count: Int
)