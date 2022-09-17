package com.example.constructioncalculator.ui.materials

import androidx.lifecycle.*
import com.example.constructioncalculator.domain.material.GetMaterialUseCase
import com.example.constructioncalculator.model.Material
import javax.inject.Inject

class MaterialViewModel @Inject constructor(private val getMaterialUseCase: GetMaterialUseCase) : ViewModel() {

    var material: LiveData<List<Material>> = getMaterialUseCase.getMaterialList.asLiveData()
}

class MaterialViewModelFactory @Inject constructor(private val getMaterialUseCase: GetMaterialUseCase): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MaterialViewModel(getMaterialUseCase) as T
    }
}