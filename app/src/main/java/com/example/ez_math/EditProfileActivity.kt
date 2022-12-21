package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ez_math.Fragments.FragmentProfile

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val btnOpenProfile = findViewById<ImageView>(R.id.btnToProfile)
        btnOpenProfile.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("from", "pengaturanProfile")
            startActivity(intent)
        }
    }



}