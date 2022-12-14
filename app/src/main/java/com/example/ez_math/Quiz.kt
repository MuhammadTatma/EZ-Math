package com.example.ez_math

data class Quiz(
    var id: String = "",
    var title: String = "",
    var question: MutableMap<String, Question> = mutableMapOf()

)
