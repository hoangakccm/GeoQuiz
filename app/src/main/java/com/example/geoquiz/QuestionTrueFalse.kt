package com.example.geoquiz

import androidx.annotation.StringRes

class QuestionTrueFalse (@StringRes textResId: Int,
                         val answer: Boolean) : Question(textResId){
    enum class Answer {
        TRUE, FALSE
    }
}