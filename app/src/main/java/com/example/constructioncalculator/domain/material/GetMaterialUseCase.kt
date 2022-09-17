package com.example.constructioncalculator.domain.material

import com.example.constructioncalculator.data.repository.MaterialRepository
import javax.inject.Inject

class GetMaterialUseCase @Inject constructor(private val materialRepository: MaterialRepository) {

    val getMaterialList = materialRepository.getAllMaterial()
}