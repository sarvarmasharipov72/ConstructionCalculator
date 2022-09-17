package com.example.constructioncalculator.ui.jobs.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.constructioncalculator.data.repository.impl.JobRepositoryImpl
import com.example.constructioncalculator.domain.job.UpdateJobUseCase
import com.example.constructioncalculator.model.Jobs
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class JobUpdateViewModel @Inject constructor(private val updateJobUseCase: UpdateJobUseCase) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    fun updateJob(jobs: Jobs) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            updateJobUseCase.updateJob(jobs)
        }
    }
}

class JobUpdateViewModelFactory @Inject constructor(private val updateJobUseCase: UpdateJobUseCase): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JobUpdateViewModel(updateJobUseCase) as T
    }
}