package com.example.constructioncalculator.ui.jobs.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.constructioncalculator.domain.job.InsertJobUseCase
import com.example.constructioncalculator.model.Material
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class InsertJobViewModel @Inject constructor(private val insertJobUseCase: InsertJobUseCase) :
    ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun insertJob(
        name: String,
        measurement: String,
        price: Int,
        materialList: List<Material>,
        count: Int
    ) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            insertJobUseCase.insertJob(
                name = name,
                measurement = measurement,
                price = price,
                materialList = materialList,
                count = count
            )
        }
    }
}

class InsertJobViewModelFactory @Inject constructor(private val insertJobUseCase: InsertJobUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InsertJobViewModel(insertJobUseCase) as T
    }
}

