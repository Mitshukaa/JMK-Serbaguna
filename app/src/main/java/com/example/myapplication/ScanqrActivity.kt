package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class ScanqrActivity : AppCompatActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var btnScan: Button
    private lateinit var tvScanResult: TextView
    private lateinit var buttonGoToBack: ImageButton

    private val CAMERA_PERMISSION_REQUEST = 1001
    private val TAG = "ScanqrActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanqr)
        supportActionBar?.hide()

        // Menghubungkan komponen UI
        barcodeView = findViewById(R.id.barcodeView)
        btnScan = findViewById(R.id.btnScan)
        tvScanResult = findViewById(R.id.tvScanResult)
        buttonGoToBack = findViewById(R.id.buttonGoToBack)

        // Tombol kembali
        buttonGoToBack.setOnClickListener {
            // Kembali ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup activity saat ini
        }

        // Memeriksa izin kamera
        checkCameraPermission()

        // Tombol untuk mulai pemindaian
        btnScan.setOnClickListener {
            startScanning()
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST
            )
        }
    }

    private fun startScanning() {
        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                tvScanResult.text = result.text
                stopScanning()
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
        })
        barcodeView.resume()
        btnScan.text = "Stop Scan"
        btnScan.setOnClickListener {
            stopScanning()
        }
    }

    private fun stopScanning() {
        barcodeView.pause()
        btnScan.text = "Start Scan"
        btnScan.setOnClickListener {
            startScanning()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanning()
            } else {
                Toast.makeText(this, "Izin kamera diperlukan untuk memindai QR code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }
}
