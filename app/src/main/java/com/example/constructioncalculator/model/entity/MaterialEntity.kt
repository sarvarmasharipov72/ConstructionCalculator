package com.example.constructioncalculator.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "material")
class MaterialEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val measurement: String,
    val price: Int,
    var percent: Byte = 0
)