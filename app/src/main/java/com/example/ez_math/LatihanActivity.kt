package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView

class LatihanActivity : AppCompatActivity() {
    lateinit var adapter: QuizAdapter
    lateinit var tvSoal: TextView
    lateinit var rbAns1: RadioButton
    lateinit var rbAns2: RadioButton
    lateinit var rbAns3: RadioButton
    lateinit var rbAns4: RadioButton
    private var quizList = mutableListOf<Quiz>()
    private var listSoal: MutableMap<String, Question> = mutableMapOf()
//    var stopwatch: Stopwatch

    var question1 : Question = Question("Berapa hasil dari 1+1?", "2", "3", "1", "4", "2")
    var question2 : Question = Question("Berapa hasil dari 1+2?", "2", "3", "1", "4", "2")
    var question3 : Question = Question("Berapa hasil dari 1+3?", "2", "3", "1", "4", "2")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latihan)
        populateDummyData()

        val KUNCI = "BukaLatihan"
        var pesan = intent.getStringExtra(KUNCI)
        tvSoal = findViewById<TextView>(R.id.tvSoal)
        rbAns1 = findViewById<RadioButton>(R.id.rbA1)
        rbAns2 = findViewById<RadioButton>(R.id.rbA2)
        rbAns3 = findViewById<RadioButton>(R.id.rbA3)
        rbAns4 = findViewById<RadioButton>(R.id.rbA4)

        tvSoal.text = question1.description
        rbAns1.setText(question1.option1)
        rbAns2.setText(question1.option2)
        rbAns3.setText(question1.option3)
        rbAns4.setText(question1.option4)

    }

    private fun setUpFireStore() {
//        val firestore: FirebaseFirestore
    }

    private fun populateDummyData() {

        listSoal.put("q1_1", question1)
        listSoal.put("q1_2", question2)
        listSoal.put("q1_3", question3)
        var quiz1: Quiz = Quiz("000001", "Pertanyaan 1", listSoal)
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

    fun fRadioButton(view: View){
        if(view is RadioButton){
            val terpilih = view.isChecked

            if(view.getId() == R.id.rbA1 && terpilih){
                question1.userAnswer = rbAns1.text as String
            }
            else if(view.getId() == R.id.rbA2 && terpilih){
                question1.userAnswer = rbAns2.text as String
            }
            else if(view.getId() == R.id.rbA3 && terpilih){
                question1.userAnswer = rbAns3.text as String
            }
            else if(view.getId() == R.id.rbA4 && terpilih){
                question1.userAnswer = rbAns4.text as String
            }
        }
    }
}