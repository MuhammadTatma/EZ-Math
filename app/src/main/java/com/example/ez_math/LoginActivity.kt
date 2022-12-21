package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var signuplink : TextView
    private lateinit var btnLogin : Button
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var pbCirc: ProgressBar
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val db  = Firebase.firestore
    private val student = com.example.ez_math.modhel.Student


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findView()

        signuplink.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        btnLogin.setOnClickListener{
            loginUser()
        }
    }

    private fun findView(){
        signuplink = findViewById<TextView>(R.id.signuplink)
        btnLogin  = findViewById<Button>(R.id.btnLogin)
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPassword)
        pbCirc = findViewById<ProgressBar>(R.id.pbCircular)
    }

    private fun loginUser(){
        var textEmail = etEmail.text.toString()
        var textPass = etPass.text.toString()

        when{
            TextUtils.isEmpty(textEmail) -> myToast("Email is required")
            TextUtils.isEmpty(textPass) -> myToast("Password is required")

            else -> {
                pbCirc.visibility = View.VISIBLE

                auth.signInWithEmailAndPassword(textEmail, textPass).addOnCompleteListener {
                    task->
                    if(task.isSuccessful){
                        pbCirc.visibility = View.GONE

                        getUserFromFirestore(FirebaseAuth.getInstance().currentUser!!.uid.toString())
//                        val intent = Intent( this@LoginActivity, MainActivity::class.java)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                        startActivity(intent)
//                        finish()
                    }else{
                        val message = task.exception!!.toString()
                        auth.signOut()
                        pbCirc.visibility = View.GONE
                        myToast("Error: $message")
                    }
                }

            }
        }
    }

    //kalo user udah login langsung dipindah ke halaman utama
    override fun onStart() {
        super.onStart()

        if(FirebaseAuth.getInstance().currentUser != null){
            getUserFromFirestore(FirebaseAuth.getInstance().currentUser!!.uid.toString())

        }
    }

    private fun getUserFromFirestore(uid: String){
        db.collection("Users").document(uid).get()
            .addOnCompleteListener { doc ->
                val result = doc.result.data as Map<String, *>
                result?.let{

                    student.email = result["email"] as String
                    student.kelas = result["kelas"] as Long
                    student.namaLengkap = result["namaLengkap"] as String
                    student.namaPengguna = result["namaPengguna"] as String
                    student.namaSekolah = result["namaSekolah"] as String
                    student.profilImage = result["profilImage"] as String
                    student.totalLatihan = result["totalLatihan"] as Long
                    student.totalXp = result["totalXp"] as Long
                    student.totalBelajar = result["totalBelajar"] as Long
                    student.tanggalLahir = result["tanggalLahir"] as String
                    student.jenisKelamin = result["jenisKelamin"] as String
                    student.uid = result["uid"] as String


                    val intent = Intent( this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("namaPengguna", student.namaPengguna)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()

                    Log.d("student" , "studentpengguna : ${student.namaPengguna}")
                }
                Log.d("loginFirestore", "datanya ${doc?.result?.data  }")
            }
            .addOnFailureListener { e ->
                Log.d("LoginFirestore" , "Error e gess : ${e.message.toString()}")
            }
    }

    private fun myToast(text: String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }


}