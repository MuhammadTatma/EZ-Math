package com.example.ez_math

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LatihanActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    //var rvLatihan: RecyclerView = findViewById(R.id.rvLatihan)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latihan)
        setUpViews()
        populateDummyData()

        val btnKembali = findViewById<Button>(R.id.btnRapli) as Button

        btnKembali.setOnClickListener{
            val intent = Intent(this, HasilLatihan::class.java)
            startActivity(intent)
        }
    }

    private fun populateDummyData() {
        quizList.add(Quiz("000001", "Pertanyaan 1"))
        quizList.add(Quiz("000002", "Pertanyaan 2"))
        quizList.add(Quiz("000003", "Pertanyaan 3"))
        quizList.add(Quiz("000004", "Pertanyaan 4"))
        quizList.add(Quiz("000005", "Pertanyaan 5"))
    }

    fun setUpViews() {
        setUpDrawerLayout()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        adapter = QuizAdapter(this, quizList)
//        rvLatihan.layoutManager = GridLayoutManager(this, 2)
//        rvLatihan.adapter = adapter
    }

    fun setUpDrawerLayout() {
//        setSupportActionBar(appBar)
//        actionBarDrawerToggle =
//            ActionBarDrawerToggle(this, "Quizzed", "Quizzed")
//        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}