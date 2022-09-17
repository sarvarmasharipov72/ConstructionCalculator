package com.example.constructioncalculator.ui.materials.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.constructioncalculator.domain.material.InsertMaterialUseCase
import com.example.constructioncalculator.model.Material
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class InsertMaterialViewModel @Inject constructor(private val insertMaterialUseCase: InsertMaterialUseCase) :
    ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun insertMaterial(
        name: String,
        measurement: String,
        price: Int,
        percent: Byte = 0
    ) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            insertMaterialUseCase.insertMaterial(
                name = name,
                measurement = measurement,
                price = price,
                percent = percent
            )
        }
    }
}

class InsertMaterialViewModelFactory @Inject constructor(private val insertMaterialUseCase: InsertMaterialUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InsertMaterialViewModel(insertMaterialUseCase) as T
    }
}