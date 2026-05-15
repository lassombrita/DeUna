package com.servicios.app.booking

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.servicios.app.R
import com.servicios.app.databinding.ActivityServiceInProgressBinding
import com.servicios.app.rating.RatingActivity

class ServiceInProgressActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityServiceInProgressBinding
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceInProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val proName = intent.getStringExtra("pro_name") ?: "Profesional"
        val proPrice = intent.getIntExtra("pro_price", 50000)

        binding.tvProName.text = proName
        binding.tvPrecio.text = "\$${String.format("%,d", proPrice)}"
        binding.tvServicio.text = "Fuga de agua en el baño"
        binding.tvEta.text = "15 - 20 min"

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnCancelar.setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setTitle("¿Cancelar servicio?")
                .setMessage("Esta acción puede tener un costo de cancelación.")
                .setPositiveButton("Sí, cancelar") { _, _ -> finish() }
                .setNegativeButton("No, mantener", null)
                .show()
        }

        binding.btnServicioCompletado.setOnClickListener {
            val i = Intent(this, RatingActivity::class.java).apply {
                putExtra("pro_name", proName)
                putExtra("pro_price", proPrice)
            }
            startActivity(i)
            finish()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_NORMAL

        val cliente = LatLng(4.6097, -74.0817)
        val pro = LatLng(4.6250, -74.0720)

        map.addMarker(MarkerOptions().position(cliente).title("Tu ubicación")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        map.addMarker(MarkerOptions().position(pro).title(intent.getStringExtra("pro_name") ?: "Profesional")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))

        map.addPolyline(PolylineOptions()
            .add(pro, cliente)
            .width(8f)
            .color(0xFF2563EB.toInt()))

        val bounds = LatLngBounds.builder().include(cliente).include(pro).build()
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 120))

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        }
    }
}