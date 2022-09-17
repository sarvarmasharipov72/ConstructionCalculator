package com.example.constructioncalculator.ui.materials.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.constructioncalculator.domain.material.UpdateMaterialUseCase
import com.example.constructioncalculator.model.Material
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialUpdateViewModel @Inject constructor(private val updateMaterialUseCase: UpdateMaterialUseCase) : ViewModel() {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun updateMaterial(id: Int, name: String, measurement: String, price: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            updateMaterialUseCase.updateMaterial(Material(id, name, measurement, price))
        }
    }
}

class MaterialUpdateViewModelFactory @Inject constructor(private val updateMaterialUseCase: UpdateMaterialUseCase): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MaterialUpdateViewModel(updateMaterialUseCase) as T
    }
}