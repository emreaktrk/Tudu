package com.akturk.tudu.map

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akturk.domain.DomainEntryPoint
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

class MapViewModel : ViewModel() {

    var presenter: Presenter? = null

    @FlowPreview
    fun load() {
        presenter?.let { presenter ->
            presenter.presentApplication().let { app ->
                val entryPoint = EntryPointAccessors
                    .fromApplication(
                        app,
                        DomainEntryPoint::class.java
                    )

                val useCase = entryPoint.getItemsUseCase()
                viewModelScope.launch(Dispatchers.IO) {
                    useCase.invoke {
                        val points = it
                            .filter { item -> item.latLng != null }
                            .map { item -> LatLng(item.latLng!!.first, item.latLng!!.second) }

                        viewModelScope.launch(Dispatchers.Main) {
                            presenter.presentPointsOfInterests(points)
                        }
                    }
                }
            }
        }
    }

    interface Presenter {
        fun presentApplication(): Application
        fun presentPointsOfInterests(points: List<LatLng>)
    }
}