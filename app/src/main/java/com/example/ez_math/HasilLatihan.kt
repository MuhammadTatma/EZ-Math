package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.ez_math.Fragments.FragmentPencapaian

class HasilLatihan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_latihan)

        val KUNCI = "BukaLatihan"
        var pesan = intent.getStringExtra(KUNCI)

    }

    fun fKePencapaian(view: View){
//        val etPesan = findViewById<EditText>(R.id.etPesan)
//        val isiPesan = etPesan.text.toString()

        val intentKePencapaian = Intent(this, FragmentPencapaian::class.java)
//        intentKeLinear.apply {
//            putExtra(KUNCI, isiPesan)
//        }
        startActivity(intentKePencapaian)
    }

}