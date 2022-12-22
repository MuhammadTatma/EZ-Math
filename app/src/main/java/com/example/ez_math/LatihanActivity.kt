package com.example.ez_math

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import com.example.ez_math.databinding.ActivityLatihanBinding
import com.google.common.base.Stopwatch
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import java.util.*
import kotlin.math.roundToInt

class LatihanActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    private var student = com.example.ez_math.modhel.Student
    lateinit var tvSoal: TextView
    lateinit var tvJmlhSoal: TextView
    lateinit var tvWaktu: TextView
    lateinit var rbAns1: RadioButton
    lateinit var rbAns2: RadioButton
    lateinit var rbAns3: RadioButton
    lateinit var rbAns4: RadioButton
    lateinit var btnPrev: Button
    lateinit var btnNext: Button
    lateinit var btnFin: Button
//    lateinit var stopwatch: Stopwatch
    var quizzes: MutableList<Quiz>? = null
    var index = 1
    var listSoal: MutableMap<String, Question>? = mutableMapOf()
    var kelasDipilih: String? = "kelas2"

    private lateinit var binding: ActivityLatihanBinding
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extra = intent.extras
        if(extra != null){
            kelasDipilih = extra.getString("kelas")
        }

        setContentView(R.layout.activity_latihan)
        findViewId()
        setUpFireStore()
        bindViews()
        setUpEventListener()

//        val hasil: Intent = getIntent()
//        var KUNCI = "BukaLatihan"
//        kelasDipilih = hasil.getStringExtra(KUNCI)!!

        binding = ActivityLatihanBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))

        startStopTimer()
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            tvWaktu.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
//        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(minutes, seconds)
    }

    private fun makeTimeString(min: Int, sec: Int): String = String.format("%02d:%02d", min, sec)

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        tvWaktu.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer() {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        timerStarted = false
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        timerStarted = true
    }

    private fun setUpEventListener() {
        btnPrev.setOnClickListener{
            index--
            bindViews()
        }
        btnNext.setOnClickListener{
            var questionRB = listSoal!!["question$index"]
            index++
            if (questionRB != null) {
                fRadioButton(questionRB)
            }
            bindViews()
        }
        btnFin.setOnClickListener{
            var questionRB = listSoal!!["question$index"]
            if (questionRB != null) {
                fRadioButton(questionRB)
            }
            quizzes!![0].time = time
            Log.d("FINALQUIZ", listSoal.toString())
            stopTimer()
            fHasil()
        }
    }

    private fun findViewId() {
        tvSoal = findViewById<TextView>(R.id.tvSoal)
        tvWaktu = findViewById<TextView>(R.id.tvWaktu)
        tvJmlhSoal = findViewById<TextView>(R.id.tvJmlhSoal)
        rbAns1 = findViewById<RadioButton>(R.id.rbA1)
        rbAns2 = findViewById<RadioButton>(R.id.rbA2)
        rbAns3 = findViewById<RadioButton>(R.id.rbA3)
        rbAns4 = findViewById<RadioButton>(R.id.rbA4)
        btnPrev = findViewById<Button>(R.id.btnPrev)
        btnNext = findViewById<Button>(R.id.btnNext)
        btnFin = findViewById<Button>(R.id.btnFin)
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("Quizzes").whereEqualTo("title", kelasDipilih)
            .get()
            .addOnSuccessListener {
                Log.d("p","hasil : ${it.documents}")
                if(it != null && !it.isEmpty){
                    quizzes = it.toObjects(Quiz::class.java)
                    listSoal = quizzes!![0].questions
                    bindViews()
                }
            }
    }

    private fun bindViews() {
        rbAns1.isChecked = false
        rbAns2.isChecked = false
        rbAns3.isChecked = false
        rbAns4.isChecked = false

        btnPrev.visibility = View.GONE
        btnNext.visibility = View.GONE
        btnFin.visibility = View.GONE

        if (index ==1) {
            btnNext.visibility = View.VISIBLE
        } else if (index == listSoal!!.size) {
            btnFin.visibility = View.VISIBLE
            btnPrev.visibility = View.VISIBLE
        } else {
            btnPrev.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }

        tvJmlhSoal.text = "${index} / ${listSoal?.size}"
        val question = listSoal!!["question$index"]
        question?.let {
            tvSoal.text = it.description
            rbAns1.setText(it.option1)
            rbAns2.setText(it.option2)
            rbAns3.setText(it.option3)
            rbAns4.setText(it.option4)
        }
    }

    val KUNCI = "HasilQuizGes"
    fun fHasil(){
        val intentKeHasil = Intent(this, HasilLatihan::class.java)
        val json: String = Gson().toJson(quizzes!![0])
        Log.d("QUIZZ", json)
        intentKeHasil.apply {
            putExtra(KUNCI, json)
        }
//        intent.putExtra("QUIZ", json)
        startActivity(intentKeHasil)
    }

    fun fRadioButton(question: Question){
        if(rbAns1.isChecked){
            question.userAnswer = rbAns1.text as String
        }
        else if(rbAns2.isChecked){
            question.userAnswer = rbAns2.text as String
        }
        else if(rbAns3.isChecked){
            question.userAnswer = rbAns3.text as String
        }
        else if(rbAns4.isChecked){
            question.userAnswer = rbAns4.text as String
        }
    }
}