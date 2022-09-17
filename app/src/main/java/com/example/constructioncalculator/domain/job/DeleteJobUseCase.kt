package com.example.constructioncalculator.domain.job

import com.example.constructioncalculator.data.repository.JobRepository
import javax.inject.Inject

class DeleteJobUseCase @Inject constructor(private val jobRepository: JobRepository) {

    suspend fun deleteJob (id: Int) {
        jobRepository.deleteJobs(id)
    }
}