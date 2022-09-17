package com.example.constructioncalculator.domain.job

import com.example.constructioncalculator.data.repository.JobRepository
import com.example.constructioncalculator.model.Jobs
import javax.inject.Inject

class UpdateJobUseCase @Inject constructor(private val jobRepository: JobRepository) {

    suspend fun updateJob (jobs: Jobs) {
        jobRepository.updateJobs(jobs)
    }
}