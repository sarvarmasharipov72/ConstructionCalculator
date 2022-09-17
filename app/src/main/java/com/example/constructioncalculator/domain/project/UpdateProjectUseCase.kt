package com.example.constructioncalculator.domain.project

import com.example.constructioncalculator.data.repository.ProjectRepository
import com.example.constructioncalculator.model.Project
import javax.inject.Inject

class UpdateProjectUseCase @Inject constructor(private val projectRepository: ProjectRepository) {

    suspend fun updateProject(project: Project) {
        projectRepository.updateProject(project = project)
    }
}