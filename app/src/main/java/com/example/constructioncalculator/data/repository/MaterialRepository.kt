package com.example.constructioncalculator.data.repository

import com.example.constructioncalculator.model.Material
import kotlinx.coroutines.flow.Flow

interface MaterialRepository {
    fun getAllMaterial(): Flow<List<Material>>
    suspend fun updateMaterial(material: Material)
    suspend fun deleteMaterial(id: Int)
    suspend fun insertMaterial(
        name: String,
        measurement: String,
        price: Int,
        percent: Byte
    )
}