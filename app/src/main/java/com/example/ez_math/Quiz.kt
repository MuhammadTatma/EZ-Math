package com.example.ez_math

data class Quiz(
    var id: String = "",
    var title: String = "",
    var questions: MutableMap<String, Question> = mutableMapOf(),
    var time: Double = 0.0
)
