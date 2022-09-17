package com.example.constructioncalculator.data.database.dao

import androidx.room.*
import com.example.constructioncalculator.model.Project
import com.example.constructioncalculator.model.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    @Query("select * from project order by name asc")
    fun getAllProjects(): Flow<List<ProjectEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProject(project: ProjectEntity)

    @Query("delete from project where id = :id")
    suspend fun deleteProject(id: Int)

    @Insert
    suspend fun insertProject(project: ProjectEntity)
}