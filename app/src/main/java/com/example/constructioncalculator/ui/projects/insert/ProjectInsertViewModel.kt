package com.example.constructioncalculator.ui.projects.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.constructioncalculator.data.repository.impl.ProjectRepositoryImpl
import com.example.constructioncalculator.domain.project.InsertProjectUseCase
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Project
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectInsertViewModel @Inject constructor(private val insertProjectUseCase: InsertProjectUseCase) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun insertProject(
        name: String,
        price: Int,
        date: String,
        jobList: List<Jobs>
    ) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            insertProjectUseCase.insertProject(
                name = name,
                price = price,
                date = date,
                jobList = jobList
            )
        }
    }
}

class ProjectInsertViewModelFactory @Inject constructor(private val insertProjectUseCase: InsertProjectUseCase): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProjectInsertViewModel(insertProjectUseCase) as T
    }
}