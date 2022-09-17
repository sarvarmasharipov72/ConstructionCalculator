package com.example.constructioncalculator.data.database.dao

import androidx.room.*
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.entity.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Query("select * from jobs")
    fun getAllJobs(): Flow<List<JobEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateJobs(jobs: JobEntity)

    @Query("delete from jobs where id = :id")
    suspend fun deleteJobs(id: Int)

    @Insert()
    suspend fun insertJobs(jobs: JobEntity)

}