package com.example.constructioncalculator.data.repository

import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getAllProject(): Flow<List<Project>>
    suspend fun updateProject(project: Project)
    suspend fun deleteProject(id: Int)
    suspend fun insertProject(
        name: String,
        price: Int,
        date: String,
        jobList: List<Jobs>
    )
}