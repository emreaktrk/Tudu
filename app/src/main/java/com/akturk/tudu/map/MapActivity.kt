package com.akturk.tudu.map

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.akturk.tudu.BaseActivity
import com.akturk.tudu.R
import com.akturk.tudu.ViewModelSupport
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.FlowPreview

class MapActivity : BaseActivity(R.layout.activity_map),
    ViewModelSupport<MapViewModel>,
    MapViewModel.Presenter,
    OnMapReadyCallback {

    override val viewModel: MapViewModel by invoke(this, MapViewModel::class.java)


    var googleMap: GoogleMap? = null

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MapActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.presenter = this

        val map =
            supportFragmentManager.findFragmentById(R.id.fragment_of_map) as SupportMapFragment
        map.getMapAsync(this)
    }

    @FlowPreview
    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
        viewModel.load()
    }

    override fun presentApplication(): Application = application

    override fun presentPointsOfInterests(points: List<LatLng>) {
        if (points.isNotEmpty()) {
            googleMap?.run {
                val bounds = LatLngBounds.Builder()
                points.forEach {
                    bounds.include(it)

                    val marker = MarkerOptions().position(it)
                    addMarker(marker)
                }

                moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 16))
            }
        }
    }
}