package com.example.constructioncalculator.ui.jobs.insert.add

import androidx.lifecycle.*
import com.example.constructioncalculator.domain.material.GetMaterialUseCase
import com.example.constructioncalculator.model.Material
import javax.inject.Inject

class AddMaterialViewModel @Inject constructor(private val getMaterialUseCase: GetMaterialUseCase) :
    ViewModel() {

    var nameList: LiveData<List<Material>> = getMaterialUseCase.getMaterialList.asLiveData()
}

class AddMaterialViewModelFactory @Inject constructor(private val getMaterialUseCase: GetMaterialUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddMaterialViewModel(getMaterialUseCase) as T
    }
}