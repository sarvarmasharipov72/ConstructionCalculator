package com.example.constructioncalculator.domain.project

import com.example.constructioncalculator.data.repository.ProjectRepository
import com.example.constructioncalculator.model.Jobs
import javax.inject.Inject

class InsertProjectUseCase @Inject constructor(private val projectRepository: ProjectRepository) {

    suspend fun insertProject(
        name: String,
        price: Int,
        date: String,
        jobList: List<Jobs>
    ) {
        projectRepository.insertProject(
            name = name,
            price = price,
            date = date,
            jobList = jobList
        )
    }
}