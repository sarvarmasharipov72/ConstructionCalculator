package com.example.constructioncalculator.ui.projects.insert.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.constructioncalculator.domain.job.GetAllJobUseCase
import com.example.constructioncalculator.model.Jobs
import javax.inject.Inject

class AddJobToProjectViewModel @Inject constructor(private val getAllJobUseCase: GetAllJobUseCase) :
    ViewModel() {
    var readAllJobs: LiveData<List<Jobs>> = getAllJobUseCase.getAllJobs.asLiveData()
}

class AddJobToProjectViewModelFactory @Inject constructor(private val getAllJobUseCase: GetAllJobUseCase): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddJobToProjectViewModel(getAllJobUseCase) as T
    }
}