package com.example.constructioncalculator.data.repository

import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Material
import kotlinx.coroutines.flow.Flow

interface JobRepository {

    fun getAllJobs(): Flow<List<Jobs>>
    suspend fun insertJobs(
        name: String,
        measurement: String,
        price: Int,
        materialList: List<Material>,
        count: Int
    )
    suspend fun deleteJobs(id: Int)
    suspend fun updateJobs(jobs: Jobs)
}