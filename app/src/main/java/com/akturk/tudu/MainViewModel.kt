package com.akturk.tudu

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.akturk.domain.DomainEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var presenter: Presenter? = null
    val search = MutableLiveData("")


    @FlowPreview
    fun observe() {
        presenter?.presentApplication()?.let {
            val entryPoint = EntryPointAccessors.fromApplication(it, DomainEntryPoint::class.java)
            val useCase = entryPoint.getFilterUseCase()
            viewModelScope.launch(Dispatchers.IO) {
                search.asFlow().debounce(1000).collect { sample ->
                    useCase.search = sample ?: ""
                    useCase.invoke { result ->
                        Log.v("RESULT", "Total item size is ${result.size}")
                    }
                }
            }
        }
    }

    fun populate(iteration: Int) {
        presenter?.presentApplication()?.let {
            val entryPoint = EntryPointAccessors.fromApplication(it, DomainEntryPoint::class.java)
            val useCase = entryPoint.getPopulateUseCase()
            viewModelScope.launch(Dispatchers.IO) {
                useCase.iteration = iteration
                useCase.invoke {
                    viewModelScope.launch(Dispatchers.Main) {
                        search.value = ""
                    }
                }
            }
        }
    }

    interface Presenter {
        fun presentApplication(): Application
    }
}