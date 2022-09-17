package com.example.constructioncalculator.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.constructioncalculator.model.Jobs

@Entity(tableName = "project")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Int,
    val date: String,
    val jobList: List<Jobs>
)