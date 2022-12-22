package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast

class DetailPencapaian : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_pencapaian)

        val btnKembali = findViewById<ImageButton>(R.id.ibKembali) as ImageButton

        btnKembali.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("from", "detailPencapaian")
            startActivity(intent)
        }
    }
}