package com.example.constructioncalculator.domain.job

import com.example.constructioncalculator.data.repository.JobRepository
import javax.inject.Inject

class GetAllJobUseCase @Inject constructor(private val jobRepository: JobRepository) {

    val getAllJobs = jobRepository.getAllJobs()
}