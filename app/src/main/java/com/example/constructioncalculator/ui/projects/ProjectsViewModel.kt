package com.example.constructioncalculator.ui.projects

import androidx.lifecycle.*
import com.example.constructioncalculator.data.repository.impl.ProjectRepositoryImpl
import com.example.constructioncalculator.domain.project.GetAllProjectUseCase
import com.example.constructioncalculator.model.Project
import javax.inject.Inject

class ProjectsViewModel @Inject constructor(private val getAllProjectUseCase: GetAllProjectUseCase) : ViewModel() {

    var project: LiveData<List<Project>> = getAllProjectUseCase.getAllProjects.asLiveData()

}

class ProjectsViewModelFactory @Inject constructor(private val getAllProjectUseCase: GetAllProjectUseCase): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProjectsViewModel(getAllProjectUseCase) as T
    }
}