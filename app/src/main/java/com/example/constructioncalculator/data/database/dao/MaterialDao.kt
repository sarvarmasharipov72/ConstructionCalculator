package com.example.constructioncalculator.data.database.dao

import androidx.room.*
import com.example.constructioncalculator.model.Material
import com.example.constructioncalculator.model.entity.MaterialEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MaterialDao {

    @Query("select * from material")
    fun getMaterialList(): Flow<List<MaterialEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMaterial(material: MaterialEntity)

    @Query("delete from material where id = :id")
    suspend fun deleteMaterial(id: Int)

    @Insert
    suspend fun insertMaterial(material: MaterialEntity)
}