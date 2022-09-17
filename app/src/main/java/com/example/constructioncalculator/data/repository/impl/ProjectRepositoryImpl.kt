package com.example.constructioncalculator.data.repository.impl

import com.example.constructioncalculator.data.database.ConstructionDatabase
import com.example.constructioncalculator.data.database.dao.ProjectDao
import com.example.constructioncalculator.data.repository.ProjectRepository
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Project
import com.example.constructioncalculator.model.entity.ProjectEntity
import com.example.constructioncalculator.model.mapper.toProject
import com.example.constructioncalculator.model.mapper.toProjectEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {

    override fun getAllProject(): Flow<List<Project>> {
        return projectDao.getAllProjects().map { list -> list.map { it.toProject() } }
    }

    override suspend fun updateProject(project: Project) {
        projectDao.updateProject(project.toProjectEntity())
    }

    override suspend fun deleteProject(id: Int) {
        projectDao.deleteProject(id)
    }

    override suspend fun insertProject(
        name: String,
        price: Int,
        date: String,
        jobList: List<Jobs>
    ) {
        projectDao.insertProject(
            ProjectEntity(
                name = name,
                price = price,
                date = date,
                jobList = jobList
            )
        )
    }


}