package com.example.ez_math

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class QuizAdapter(val context: Context, val quizzes: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.latihan_item, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }
}