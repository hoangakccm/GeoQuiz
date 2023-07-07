package com.example.geoquiz

import androidx.annotation.StringRes

class QuestionMultipleChoice (@StringRes textResId: Int,
                              val choices: List<String>,
                              val correctAnswerIndices: List<Int>)
    : Question(textResId)