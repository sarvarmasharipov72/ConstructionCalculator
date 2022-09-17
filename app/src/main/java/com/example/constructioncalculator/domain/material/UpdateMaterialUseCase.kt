package com.example.constructioncalculator.domain.material

import com.example.constructioncalculator.data.repository.MaterialRepository
import com.example.constructioncalculator.model.Material
import javax.inject.Inject

class UpdateMaterialUseCase @Inject constructor(private val materialRepository: MaterialRepository) {

    suspend fun updateMaterial (material: Material) {
        materialRepository.updateMaterial(material)
    }
}