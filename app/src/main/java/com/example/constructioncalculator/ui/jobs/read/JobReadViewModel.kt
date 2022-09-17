package com.example.constructioncalculator.ui.jobs.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.constructioncalculator.data.repository.impl.JobRepositoryImpl
import com.example.constructioncalculator.domain.job.DeleteJobUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class JobReadViewModel @Inject constructor(private val deleteJobUseCase: DeleteJobUseCase) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    fun deleteJob(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            deleteJobUseCase.deleteJob(id)
        }
    }
}

class JobReadViewModelFactory @Inject constructor(private val deleteJobUseCase: DeleteJobUseCase): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JobReadViewModel(deleteJobUseCase) as T
    }
}