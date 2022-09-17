package com.example.constructioncalculator.domain.material

import com.example.constructioncalculator.data.repository.MaterialRepository
import javax.inject.Inject

class InsertMaterialUseCase @Inject constructor(private val materialRepository: MaterialRepository) {

    suspend fun insertMaterial(
        name: String,
        measurement: String,
        price: Int,
        percent: Byte = 0
    ) {
        materialRepository.insertMaterial(
            name = name,
            measurement = measurement,
            price = price,
            percent = percent
        )
    }
}