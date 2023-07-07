package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var trueButton: ImageView
    private lateinit var falseButton: ImageView
    private lateinit var nextButton: Button
    private lateinit var TrueFalesOption: LinearLayout
    private lateinit var checkBox: CheckBox
    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var settingBtn: ImageView
    private lateinit var sharedPrefUtil: SharedPrefUtil
    private lateinit var toolbar: MaterialToolbar
    private lateinit var displayMode: RadioGroup
    private lateinit var languageMode: RadioGroup


    private var curentIndex = 0
    private val questionBank = listOf(
        QuestionTrueFalse(R.string.question_oceans, true),
        QuestionTrueFalse(R.string.question_africa, true),
        QuestionTrueFalse(R.string.question_asia,true),
        QuestionTrueFalse(R.string.question_americas, false),
        QuestionTrueFalse(R.string.question_mideast, false)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restoreUIStates(savedInstanceState)
        initView()
        initUIEventHandlers()
        //loadSettings()


    }
    private fun restoreUIStates(inState : Bundle?){
        if(inState!=null)
            curentIndex = inState.getInt("currentIndex")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("currentIndex", curentIndex)
        super.onSaveInstanceState(outState)
    }

    private fun updateUI() {
        var question = questionBank[curentIndex].textResId
        questionTextView.setText(question)
    }
    private fun checkAnswer(answer :Boolean){

        val question =questionBank[curentIndex].answer
        if(question== answer)
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()

    }
    private fun checkAnswer2(answer: Answer) {
        val question = questionBank[curentIndex]
        val isCorrect = (question.answer)
        val toastMessageResId = if (isCorrect) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, toastMessageResId, Toast.LENGTH_SHORT).show()
    }

    enum class Answer {
        TRUE, FALSE
    }
//    private fun loadSettings() {
//        if(sharedPrefUtil.isDarkMode()){
//            displayMode.findViewById<RadioButton>(R.id.darkmode).isChecked= true
//        }
//        if(sharedPrefUtil.isEngMode()){
//            languageMode.findViewById<RadioButton>(R.id.Engmode).isChecked= true
//        }
//    }
    private fun initUIEventHandlers() {
        nextButton.setOnClickListener {
            curentIndex= (curentIndex+1) % questionBank.size
            updateUI()
            if(curentIndex>=3){
                TrueFalesOption.visibility = View.GONE
                checkBox.visibility = View.VISIBLE
                checkBox1.visibility = View.VISIBLE
                checkBox2.visibility = View.VISIBLE
            }else{
                checkBox.visibility = View.GONE
                checkBox1.visibility = View.GONE
                checkBox2.visibility = View.GONE
                TrueFalesOption.visibility = View.VISIBLE
            }
        }
        trueButton.setOnClickListener {
            checkAnswer2(Answer.TRUE)
        }
        falseButton.setOnClickListener {
            checkAnswer2(Answer.FALSE)
        }
        settingBtn.setOnClickListener{
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)

        }
        toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.settings_id -> {
                    startActivity(SettingActivity.getIntent(this))
                    Toast.makeText(this, "setting",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.about_id -> {
                    Toast.makeText(this, "about",Toast.LENGTH_SHORT).show()

                    true
                }
                else -> false
            }
        }


    }

    private fun initView() {
        questionTextView = findViewById(R.id.questionTextview)
        trueButton = findViewById(R.id.imageButtonTrue)
        falseButton = findViewById(R.id.imageButtonFalse)
        nextButton = findViewById(R.id.nextButton)
        TrueFalesOption = findViewById(R.id.TrueFalesOption)
        checkBox = findViewById(R.id.checkBox)
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        settingBtn = findViewById(R.id.setting)
        toolbar = findViewById(R.id.toolbar_id)

        updateUI()
    }
}