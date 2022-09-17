package com.example.constructioncalculator.data.repository.impl

import com.example.constructioncalculator.data.database.dao.JobDao
import com.example.constructioncalculator.data.repository.JobRepository
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Material
import com.example.constructioncalculator.model.entity.JobEntity
import com.example.constructioncalculator.model.mapper.toJob
import com.example.constructioncalculator.model.mapper.toJobEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val jobDao: JobDao
) : JobRepository {

    override suspend fun updateJobs(jobs: Jobs) {
        jobDao.updateJobs(jobs.toJobEntity())
    }

    override suspend fun deleteJobs(id: Int) {
        jobDao.deleteJobs(id)
    }

    override fun getAllJobs(): Flow<List<Jobs>> {
        return jobDao.getAllJobs().map { list -> list.map { it.toJob() } }
    }

    override suspend fun insertJobs(
        name: String,
        measurement: String,
        price: Int,
        materialList: List<Material>,
        count: Int
    ) {
        jobDao.insertJobs(
            JobEntity(
                name = name,
                measurement = measurement,
                price = price,
                materialList = materialList,
                count = count
            )
        )
    }
}