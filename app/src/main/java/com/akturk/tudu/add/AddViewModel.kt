package com.akturk.tudu.add

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akturk.domain.DomainEntryPoint
import com.akturk.domain.model.TodoItem
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddViewModel : ViewModel() {

    var presenter: Presenter? = null

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val due = MutableLiveData<Calendar>()

    fun save() {
        presenter?.let { presenter ->
            presenter.presentApplication().let { app ->
                val entryPoint = EntryPointAccessors
                    .fromApplication(
                        app,
                        DomainEntryPoint::class.java
                    )

                val useCase = entryPoint.getAddUseCase()
                viewModelScope.launch(Dispatchers.IO) {
                    useCase.invoke {
                        return@invoke TodoItem(
                            title.value,
                            description.value,
                            Pair(29.123123, 27.123123),
                            mutableSetOf("Android", "Tech", "Mobile")
                        )
                    }
                }
            }

            due.value?.let {
                presenter.presentAlarm(it.timeInMillis)
            }

            presenter.navigateBack()
        }
    }

    fun dueTime() {
        presenter?.presentDatePicker()
    }

    interface Presenter {
        fun presentApplication(): Application
        fun presentDatePicker()
        fun presentAlarm(timeInMillis: Long): Boolean
        fun navigateBack()
    }
}