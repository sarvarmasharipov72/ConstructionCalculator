package com.example.constructioncalculator.domain.job

import com.example.constructioncalculator.data.repository.JobRepository
import com.example.constructioncalculator.model.Material
import javax.inject.Inject

class InsertJobUseCase @Inject constructor(private val jobRepository: JobRepository) {

    suspend fun insertJob(
        name: String,
        measurement: String,
        price: Int,
        materialList: List<Material>,
        count: Int
    ) {
        jobRepository.insertJobs(
            name = name,
            measurement = measurement,
            price = price,
            materialList = materialList,
            count = count
        )
    }
}