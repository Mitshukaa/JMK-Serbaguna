package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map) // Pastikan layout XML Anda sesuai

        supportActionBar?.hide()

        // Menginisialisasi peta
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Menangani tombol kembali
        val buttonGoToBack = findViewById<ImageButton>(R.id.buttonGoToBack)
        buttonGoToBack.setOnClickListener {
            finish()
        }

        // Menangani tombol lokasi saya
        val fabMyLocation = findViewById<FloatingActionButton>(R.id.fabMyLocation)
        fabMyLocation.setOnClickListener {
            getMyLocation()
        }

        // Menangani tombol zoom in
        val fabZoomIn = findViewById<FloatingActionButton>(R.id.fabZoomIn)
        fabZoomIn.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomIn())
        }

        // Menangani tombol zoom out
        val fabZoomOut = findViewById<FloatingActionButton>(R.id.fabZoomOut)
        fabZoomOut.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomOut())
        }

        // Menangani pencarian lokasi
        val searchBar = findViewById<EditText>(R.id.searchBar)
        searchBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchLocation(v.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getMyLocation()
    }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST
            )
            return
        }

        mMap.isMyLocationEnabled = true

        // Set lokasi awal ke SMKN 2 Yogyakarta
        val smkn2Yogyakarta = LatLng(-7.783226, 110.367520)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(smkn2Yogyakarta, 15f))

        // Mengambil lokasi terakhir pengguna
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
            }
        }
    }

    private fun searchLocation(locationName: String) {
        val geocoder = Geocoder(this)
        try {
            val addressList: List<Address>? = geocoder.getFromLocationName(locationName, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                val address = addressList[0]
                val latLng = LatLng(address.latitude, address.longitude)
                mMap.addMarker(MarkerOptions().position(latLng).title(locationName))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getMyLocation()
            }
        }
    }
}
