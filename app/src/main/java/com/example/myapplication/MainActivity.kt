package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // Button untuk Kalkulator
        val nextCalculator: Button = findViewById(R.id.btn_calculator)
        nextCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }

        // Button untuk Scan QR Code
        val nextScanQRCode: Button = findViewById(R.id.btn_scan_qr)
        nextScanQRCode.setOnClickListener {
            val intent = Intent(this, ScanqrActivity::class.java)
            startActivity(intent)
        }

        // Button untuk Peta
        val nextMap: Button = findViewById(R.id.btn_map)
        nextMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        // Button untuk Adiwiyata
        val nextAdiwiyata: Button = findViewById(R.id.btn_adiwiyata)
        nextAdiwiyata.setOnClickListener {
            val intent = Intent(this, AdiwiyataActivity::class.java)
            startActivity(intent)
        }

        // Button untuk ToDo List
        val nextToDoList: Button = findViewById(R.id.btn_todolist)
        nextToDoList.setOnClickListener {
            val intent = Intent(this, ToDolistActivity::class.java)
            startActivity(intent)
        }
    }
}
