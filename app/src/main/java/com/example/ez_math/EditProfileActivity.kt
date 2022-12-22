package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.ez_math.Fragments.FragmentProfile

class EditProfileActivity : AppCompatActivity() {

    private val student = com.example.ez_math.modhel.Student

    private lateinit var etNamaLengkap: EditText
    private lateinit var etKelas: EditText
    private lateinit var etNamaSekolah: EditText
    private lateinit var etEmail: EditText
    private lateinit var etTanggalahir: EditText
    private lateinit var jenisKelamin: RadioButton
    private lateinit var etPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val btnOpenProfile = findViewById<ImageView>(R.id.btnToProfile)
        btnOpenProfile.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("from", "pengaturanProfile")
            startActivity(intent)
        }

        findView()

        etNamaLengkap.setText(student.namaLengkap)
        etEmail.setText(student.email)
        etNamaSekolah.setText(student.namaSekolah)
        etKelas.setText(student.kelas.toString())
        etTanggalahir.setText(student.tanggalLahir)
//        etJenisKelamin.setText(student.jenisKelamin)
//        etPassword.setText(student.password)

    }

    private fun findView(){
        etNamaLengkap = findViewById(R.id.etNamaLengkap)
        etEmail = findViewById(R.id.etEmail)
        etNamaSekolah = findViewById(R.id.etNamaSekolah)
        etKelas = findViewById(R.id.etKelas)
        etTanggalahir = findViewById(R.id.etTanggalLahir)
//        jenisKelamin = findViewById(R.id.jenisKelamin)
        etPassword = findViewById(R.id.etPassword)
    }



}