package com.example.constructioncalculator.ui.materials.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.constructioncalculator.domain.material.DeleteMaterialUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialReadViewModel @Inject constructor(private val deleteMaterialUseCase: DeleteMaterialUseCase) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun deleteMaterial(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            deleteMaterialUseCase.deleteMaterial(id)
        }
    }
}

class MaterialReadViewModelFactory @Inject constructor(private val deleteMaterialUseCase: DeleteMaterialUseCase): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MaterialReadViewModel(deleteMaterialUseCase) as T
    }
}