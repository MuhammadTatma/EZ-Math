package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ez_math.Fragments.FragmentPencapaian
import com.example.ez_math.Fragments.FragmentProfile
import com.google.gson.Gson
import kotlin.math.roundToInt

class HasilLatihan : AppCompatActivity() {
    lateinit var quiz: Quiz
    lateinit var tvSkor: TextView
    lateinit var tvBenar: TextView
    lateinit var tvSalah: TextView
    lateinit var tvTdkJawab: TextView
    lateinit var tvTotalSoal: TextView
    lateinit var tvXP: TextView
    lateinit var tvWaktuKerja: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_latihan)
        findViewId()
        setUpViews()

        val btnKePencapaian = findViewById<LinearLayout>(R.id.toPencapaian)
        btnKePencapaian.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("from", "hasilLatihan")
            startActivity(intent)

        }
    }

    private fun findViewId() {
        tvSkor = findViewById<TextView>(R.id.tvSkor)
        tvBenar = findViewById<TextView>(R.id.tvBenar)
        tvSalah = findViewById<TextView>(R.id.tvSalah)
        tvTdkJawab = findViewById<TextView>(R.id.tvTdkJawab)
        tvTotalSoal = findViewById<TextView>(R.id.tvTotalSoal)
        tvXP = findViewById<TextView>(R.id.tvXP)
        tvWaktuKerja = findViewById<TextView>(R.id.tvWaktuKerja)
    }

    val KUNCI = "HasilQuizGes"
    private fun setUpViews() {
        val quizData: String? = intent.getStringExtra(KUNCI)
        Log.d("INIQUIZ", quizData.toString())
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
    }

    private fun calculateScore() {
        var score = 0
        var rightAns = 0
        var wrongAns = 0
        var notAns = 0
        for (entry : MutableMap.MutableEntry<String, Question> in quiz.questions.entries) {
            val question : Question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
                rightAns++
            } else wrongAns++
            if (question.userAnswer == "") notAns++
        }

        calculateXP(score)

        tvSkor.text = score.toString()
        tvTotalSoal.text = quiz.questions.size.toString()
        tvBenar.text = rightAns.toString()
        tvSalah.text = wrongAns.toString()
        tvTdkJawab.text = notAns.toString()
        tvWaktuKerja.text = quiz.time.toString()
    }

    private fun calculateXP(score: Int) : Int {
        var time: Int
        time = quiz.time.roundToInt()
        var XP: Int = score*100/time
        tvXP.text = XP.toString()

        return XP
    }

    private fun moveToFragment(fragment: Fragment)
    {
        val fragmentTrans = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.fragmentContainer, fragment)
        fragmentTrans.commit()
    }

}