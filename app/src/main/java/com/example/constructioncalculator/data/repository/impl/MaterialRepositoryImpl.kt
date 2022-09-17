package com.example.constructioncalculator.data.repository.impl

import com.example.constructioncalculator.data.database.dao.MaterialDao
import com.example.constructioncalculator.data.repository.MaterialRepository
import com.example.constructioncalculator.model.Material
import com.example.constructioncalculator.model.entity.MaterialEntity
import com.example.constructioncalculator.model.mapper.toMaterial
import com.example.constructioncalculator.model.mapper.toMaterialEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class MaterialRepositoryImpl @Inject constructor(
    private val materialDao: MaterialDao
) : MaterialRepository {

    override fun getAllMaterial(): Flow<List<Material>> {
        return materialDao.getMaterialList()
            .map { list ->
                list.map {
                    it.toMaterial()
                }
            }
    }

    override suspend fun updateMaterial(
        material: Material
    ) {
        materialDao.updateMaterial(
            material = material.toMaterialEntity()
        )
    }

    override suspend fun deleteMaterial(id: Int) {
        materialDao.deleteMaterial(id = id)
    }

    override suspend fun insertMaterial(
        name: String,
        measurement: String,
        price: Int,
        percent: Byte
    ) {
        materialDao.insertMaterial(
            MaterialEntity(
                name = name,
                measurement = measurement,
                price = price,
                percent = percent
            )
        )
    }
}