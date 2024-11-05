package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AdiwiyataActivity : AppCompatActivity() {

    private lateinit var buttonGoToBack: ImageButton
    private lateinit var buttonGoToLink: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiwiyata) // Pastikan nama file layout sesuai
        supportActionBar?.hide()

        // Menginisialisasi tombol dengan findViewById
        buttonGoToBack = findViewById(R.id.buttonGoToBack)
        buttonGoToLink = findViewById(R.id.buttonGoToLink)

        // Tombol kembali
        buttonGoToBack.setOnClickListener {
            // Kembali ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup activity saat ini
        }

        // Tombol Kunjungi Link
        buttonGoToLink.setOnClickListener {
            // Ganti dengan URL yang diinginkan
            val url = "https://www.smk2-yk.sch.id/berita/adiwiyata-smk-n-2-yogyakarta-mendapat-bantuan-tree-planting-dari-pt-komatsu-indonesia"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}
