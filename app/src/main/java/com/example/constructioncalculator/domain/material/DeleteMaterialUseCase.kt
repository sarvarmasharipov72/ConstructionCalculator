package com.example.constructioncalculator.domain.material

import com.example.constructioncalculator.data.repository.MaterialRepository
import javax.inject.Inject

class DeleteMaterialUseCase @Inject constructor(private val materialRepository: MaterialRepository) {

    suspend fun deleteMaterial(materialId: Int) {
        materialRepository.deleteMaterial(materialId)
    }
}