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
//        addData()

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

    fun addData(){
        val datane = hashMapOf(
            "title" to "kelas6",
            "questions" to
                hashMapOf(
                    "question1" to hashMapOf(
                        "answer" to "4",
                        "description" to "2+2",
                        "option1" to "5",
                        "option2" to "4",
                        "option3" to "6",
                        "option4" to "3"),
                    "question2"  to hashMapOf(
                        "answer" to "7",
                        "description" to "4+3",
                        "option1" to "6",
                        "option2" to "8",
                        "option3" to "7",
                        "option4" to "9"),
                    "question3"  to hashMapOf(
                        "answer" to "2",
                        "description" to "5-3",
                        "option1" to "3",
                        "option2" to "4",
                        "option3" to "8",
                        "option4" to "2"),
                    "question4"  to hashMapOf(
                        "answer" to "2",
                        "description" to "7-5",
                        "option1" to "4",
                        "option2" to "2",
                        "option3" to "3",
                        "option4" to "13"),
                    "question5"  to hashMapOf(
                        "answer" to "6",
                        "description" to "3+3",
                        "option1" to "6",
                        "option2" to "3",
                        "option3" to "0",
                        "option4" to "4"),
                    "question6"  to hashMapOf(
                        "answer" to "4",
                        "description" to "Ani mempunyai 2 pensil lalu ibu membelikannya 2 pensil. Jadi berapakah pensil miliki Ani …..",
                        "option1" to "0",
                        "option2" to "2",
                        "option3" to "4",
                        "option4" to "6"),
                    "question7"  to hashMapOf(
                        "answer" to "4",
                        "description" to "Ayah memberikan Iko 3 pena lalu ibu juga membelikan Iko 1 pena. Jadi berapakah jumlah pena Iko sekarang ……",
                        "option1" to "6",
                        "option2" to "3",
                        "option3" to "5",
                        "option4" to "4"),
                    "question8"  to hashMapOf(
                        "answer" to "3",
                        "description" to "Adi membeli 1 buah penghapus lalu ayah membelikan Adi 1 penghapus dan ibu juga membelikannay 1 buah penghapus. Maka penghapus Adi sekarang ada ……",
                        "option1" to "1",
                        "option2" to "3",
                        "option3" to "2",
                        "option4" to "4"),
                    "question9"  to hashMapOf(
                        "answer" to "5",
                        "description" to "Ina dibelikan 2 penggaris oleh ayah, dan ibu membelikan 3 penggaris, maka berapakah jumlah keseluruhan penggaris Ina sekarang ….",
                        "option1" to "6",
                        "option2" to "2",
                        "option3" to "5",
                        "option4" to "3"),
                    "question10"  to hashMapOf(
                        "answer" to "4",
                        "description" to "Ati mempunyai 6 buah pensil lalu ia berikan kepada Ani 2 buah pensil. Jadi jumlah pensil Ati sekarang adalah……",
                        "option1" to "6",
                        "option2" to "3",
                        "option3" to "5",
                        "option4" to "4")

                )
        )

        db.collection("Quizzes").add(datane)
            .addOnSuccessListener {
                Log.d("Quizzes", "berhasil nambah gesss")
            }
            .addOnFailureListener {
                Log.d("Quizzes", "duh gagal sabar mas")
            }
    }


}