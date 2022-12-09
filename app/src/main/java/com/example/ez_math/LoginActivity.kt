package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signuplink = findViewById<TextView>(R.id.signuplink)
        signuplink.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

    }
}