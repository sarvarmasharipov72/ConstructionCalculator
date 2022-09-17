package com.example.constructioncalculator.ui.projects.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.constructioncalculator.data.repository.impl.ProjectRepositoryImpl
import com.example.constructioncalculator.domain.project.DeleteProjectUseCase
import com.example.constructioncalculator.domain.project.GetAllProjectUseCase
import com.example.constructioncalculator.ui.projects.ProjectsViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectReadViewModel @Inject constructor(private val deleteProjectUseCase: DeleteProjectUseCase): ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun deleteProject(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            deleteProjectUseCase.deleteProject(id)
        }
    }
}

class ProjectReadViewModelFactory @Inject constructor(private val deleteProjectUseCase: DeleteProjectUseCase): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProjectReadViewModel(deleteProjectUseCase) as T
    }
}