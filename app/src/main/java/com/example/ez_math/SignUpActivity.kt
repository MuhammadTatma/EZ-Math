package com.example.ez_math

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit  var tvLoginLink:TextView
    private lateinit var btnSignup:Button
    private lateinit var etNamaLengkap:EditText
    private lateinit var etEmail:EditText
    private lateinit var etPassword:EditText
    private lateinit var  progressBar:ProgressBar
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    private val student = com.example.ez_math.modhel.Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        findView()


        tvLoginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnSignup.setOnClickListener {
            createAccount()
        }
    }

    private fun findView(){
        tvLoginLink = findViewById(R.id.loginlink)
        etNamaLengkap = findViewById(R.id.namaLengkap)
        etEmail = findViewById(R.id.emailID)
        etPassword = findViewById(R.id.password)
        btnSignup = findViewById(R.id.signupBtn)
        progressBar = findViewById(R.id.pbCircular)
    }

    private fun createAccount(){
        val namaLengkap = etNamaLengkap.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        when{
            TextUtils.isEmpty(namaLengkap) -> myToast("Nama lengkap masih kosong lur")
            TextUtils.isEmpty(email) -> myToast("Email masih kosong lur")
            TextUtils.isEmpty(password) -> myToast("password masih kosong lur")

            else -> {

                progressBar.visibility = View.VISIBLE



                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            saveUserToFirestore(namaLengkap, email)
                        }else{
                            val message = task.exception!!.toString()
                            auth.signOut()
                            progressBar.visibility = View.GONE
                            myToast("Error: $message")
                        }
                    }
            }
        }
    }

    private fun myToast(text: String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    private fun saveUserToFirestore(namaLengkap: String, email: String){
        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid

        val userMap = HashMap<String,Any>()
        userMap["uid"] = currentUserId
        userMap["namaLengkap"] = namaLengkap
        userMap["email"] = email
        userMap["namaPengguna"] = namaLengkap
        userMap["namaSekolah"] = ""
        userMap["kelas"] = 0
        userMap["totalBelajar"] = 0
        userMap["totalLatihan"] = 0
        userMap["totalXp"] = 0
        userMap["tanggalLahir"] = "00-00-0000"
        userMap["jenisKelamin"] = ""
        userMap["profilImage"] = "https://firebasestorage.googleapis.com/v0/b/ezmath-45ad1.appspot.com/o/Default%20Image%2Fprofile.png?alt=media&token=9ca73d03-6b6c-4982-9f43-6752485247fe"


        db.collection("Users").document(currentUserId)
            .set(userMap)
            .addOnCompleteListener {
                Toast.makeText(application, "User Data Saved Successfully to Firestore", Toast.LENGTH_SHORT).show()

                student.email = email
                student.kelas = 0
                student.namaLengkap = namaLengkap
                student.namaPengguna = namaLengkap
                student.namaSekolah = ""
                student.profilImage = "https://firebasestorage.googleapis.com/v0/b/ezmath-45ad1.appspot.com/o/Default%20Image%2Fprofile.png?alt=media&token=9ca73d03-6b6c-4982-9f43-6752485247fe"
                student.totalLatihan = 0
                student.totalXp = 0
                student.totalBelajar = 0
                student.jenisKelamin = ""
                student.tanggalLahir = "00-00-0000"
                student.uid = currentUserId

                //pindah ke halaman utama
                val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                    e -> Toast.makeText(application, e.message.toString(), Toast.LENGTH_SHORT)
                .show()
                Log.e("Firestore", e.message.toString())
            }
    }

    private fun saveUserIngpo(namaLengkap:String, email:String){
        val currentUSerId = FirebaseAuth.getInstance().currentUser!!.uid
        val userRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

        val userMap = HashMap<String,Any>()
        userMap["uid"] = currentUSerId
        userMap["namaLengkap"] = namaLengkap
        userMap["email"] = email
        userMap["namaPengguna"] = ""
        userMap["namaSekolah"] = ""
        userMap["kelas"] = 0
        userMap["totlaBelajar"] = 0
        userMap["totalLatihan"] = 0
        userMap["totalXp"] = 0
        userMap["profilImage"] = "https://firebasestorage.googleapis.com/v0/b/ezmath-45ad1.appspot.com/o/Default%20Image%2Fprofile.png?alt=media&token=9ca73d03-6b6c-4982-9f43-6752485247fe"

        userRef.child(currentUSerId).setValue(userMap)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    progressBar.visibility = View.GONE
                    myToast("Alhamdulillah gess berhasil")


                }else{
                    val message = task.exception!!.toString()
                    auth.signOut()
                    progressBar.visibility = View.GONE
                    myToast("Error: $message")
                }

            }
    }
}