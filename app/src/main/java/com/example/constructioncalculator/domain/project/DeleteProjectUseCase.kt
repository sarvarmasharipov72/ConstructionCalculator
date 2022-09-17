package com.example.constructioncalculator.domain.project

import com.example.constructioncalculator.data.repository.ProjectRepository
import javax.inject.Inject

class DeleteProjectUseCase @Inject constructor(private val projectRepository: ProjectRepository) {

    suspend fun deleteProject (id: Int) {
        projectRepository.deleteProject(id)
    }
}