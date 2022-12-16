package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class LatihanActivity : AppCompatActivity() {
    lateinit var adapter: QuizAdapter
    lateinit var tvSoal: TextView
    lateinit var rbAns1: RadioButton
    lateinit var rbAns2: RadioButton
    lateinit var rbAns3: RadioButton
    lateinit var rbAns4: RadioButton
    lateinit var btnPrev: Button
    lateinit var btnNext: Button
    lateinit var btnFin: Button
    var quizzes: MutableList<Quiz>? = null
    var index = 1
    private var quizList = mutableListOf<Quiz>()
    private var listSoal: MutableMap<String, Question>? = mutableMapOf()
//    var stopwatch: Stopwatch

    var question1 : Question = Question("Berapa hasil dari 1+1?", "2", "3", "1", "4", "2")
    var question2 : Question = Question("Berapa hasil dari 1+2?", "2", "3", "1", "4", "2")
    var question3 : Question = Question("Berapa hasil dari 1+3?", "2", "3", "1", "4", "2")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latihan)
        populateDummyData()
        findViewId()
        bindViews()
        setUpEventListener()

//        val hasil: Intent = getIntent()
        val KUNCI = "BukaLatihan"
//        val kelasDipilih: String? = hasil.getStringExtra(KUNCI)
        var pesan = intent.getStringExtra(KUNCI)
    }

    private fun setUpEventListener() {
        btnPrev.setOnClickListener{
            index--
            bindViews()
        }
        btnNext.setOnClickListener{
            var questionRB = listSoal!!["q1_$index"]
            index++
            if (questionRB != null) {
                fRadioButton(questionRB)
            }
            bindViews()
        }
        btnFin.setOnClickListener{
            var questionRB = listSoal!!["q1_$index"]
            if (questionRB != null) {
                fRadioButton(questionRB)
            }
            Log.d("FINALQUIZ", listSoal.toString())
        }
    }

    private fun findViewId() {
        tvSoal = findViewById<TextView>(R.id.tvSoal)
        rbAns1 = findViewById<RadioButton>(R.id.rbA1)
        rbAns2 = findViewById<RadioButton>(R.id.rbA2)
        rbAns3 = findViewById<RadioButton>(R.id.rbA3)
        rbAns4 = findViewById<RadioButton>(R.id.rbA4)
        btnPrev = findViewById<Button>(R.id.btnPrev)
        btnNext = findViewById<Button>(R.id.btnNext)
        btnFin = findViewById<Button>(R.id.btnFin)
    }

    private fun setUpFireStore() {
//        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
//        firestore.collection("quizzes").whereEqualTo("title", "Kelas${pesan}")
//            .get()
//            .addOnSuccessListener {
//                if(it != null && !it.isEmpty){
//                    quizzes = it.toObjects(Quiz::class.java)
//                    listSoal = quizzes!![0].questions
//                    bindViews()
//                }
//            }
    }

    private fun bindViews() {
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

        rbAns1.isChecked = false
        rbAns2.isChecked = false
        rbAns3.isChecked = false
        rbAns4.isChecked = false

        val question = listSoal!!["q1_$index"]
        question?.let {
            tvSoal.text = it.description
            rbAns1.setText(it.option1)
            rbAns2.setText(it.option2)
            rbAns3.setText(it.option3)
            rbAns4.setText(it.option4)
        }
    }

    private fun populateDummyData() {

        listSoal!!.put("q1_1", question1)
        listSoal!!.put("q1_2", question2)
        listSoal!!.put("q1_3", question3)
        val quiz1: Quiz = Quiz("000001", "Pertanyaan 1", listSoal!!)
        quizList.add(quiz1)
        quizList.add(Quiz("000002", "Pertanyaan 2"))
        quizList.add(Quiz("000003", "Pertanyaan 3"))
        quizList.add(Quiz("000004", "Pertanyaan 4"))
        quizList.add(Quiz("000005", "Pertanyaan 5"))
        quizList.add(Quiz("000006", "Pertanyaan 6"))
    }

    fun fHasil(view: View){
        val intentKeHasil = Intent(this, HasilLatihan::class.java)
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