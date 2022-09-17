package com.example.constructioncalculator.domain.project

import com.example.constructioncalculator.data.repository.ProjectRepository
import javax.inject.Inject

class GetAllProjectUseCase @Inject constructor(private val projectRepository: ProjectRepository) {

    val getAllProjects = projectRepository.getAllProject()
}