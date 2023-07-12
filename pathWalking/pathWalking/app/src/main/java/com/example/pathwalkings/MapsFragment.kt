package com.example.pathwalkings

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.example.pathwalkings.MapsFragmentArgs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.pathwalkings.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsFragment() : Fragment(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapsBinding
    private  var latitude : Float = 0f
    private  var longitude : Float = 0f
    private var name   : String = ""
    val args: com.example.pathwalkings.MapsFragmentArgs by navArgs()

    // OnMapReadyCallback harita hazır olduğunda kullanılbailcek bir arayüzdür
    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap


          val location = LatLng(latitude.toDouble(),longitude.toDouble() )
          googleMap.addMarker(MarkerOptions().position(location).title(name)) //başlık verme
          googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15f))

    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentMapsBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            this.name = args.locName
            this.latitude = args.latitude
            this.longitude = args.longtude
            mapFragment?.getMapAsync(callback)
        }


    }
