package com.example.constructioncalculator.ui.jobs

import androidx.lifecycle.*
import com.example.constructioncalculator.data.repository.impl.JobRepositoryImpl
import com.example.constructioncalculator.domain.job.GetAllJobUseCase
import com.example.constructioncalculator.model.Jobs
import javax.inject.Inject

class JobsViewModel @Inject constructor(private val getAllJobUseCase: GetAllJobUseCase) : ViewModel() {

    var jobs: LiveData<List<Jobs>> = getAllJobUseCase.getAllJobs.asLiveData()
}

class JobsViewModelFactory @Inject constructor(private val getAllJobUseCase: GetAllJobUseCase): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JobsViewModel(getAllJobUseCase) as T
    }
}