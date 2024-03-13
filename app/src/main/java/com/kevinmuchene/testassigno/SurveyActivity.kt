package com.kevinmuchene.testassigno

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SurveyActivity : AppCompatActivity() {


    private lateinit var tvSurveyResults: TextView
    private lateinit var submitSurveyBtn: Button

    private val TAG = "Preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey);

        tvSurveyResults = findViewById(R.id.tvSurveyResults)
        submitSurveyBtn = findViewById(R.id.submitPreferencesBtnId)

        var allQuestionsContainer =findViewById<LinearLayout>(R.id.llQuestionsContainer);
        var surveyType = getIntent().getStringExtra("surveyType");

        if ("FoodPreferences" == surveyType) {
            addFoodPreferenceQuestions(allQuestionsContainer);
        } else {
            addDietPreferenceQuestions(allQuestionsContainer)
        }

        submitSurveyBtn.setOnClickListener {
            if (areAllQuestionsAnswered()) {
                showSurveyResults()
            } else {

                Toast.makeText(this, "Please answer all questions before submitting.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun addFoodPreferenceQuestions(container: LinearLayout) {
        addQuestionWithRadioButtons(
            container,
            "What is your favorite cuisine?",
            arrayOf<String>("Chinese", "French", "Italian", "Indian", "Japanese", "Thai", "Turkish")
        )
        addQuestionWithRadioButtons(
            container,
            "How often do you eat out?",
            arrayOf<String>("Never", "Rarely", "Sometimes", "Frequently")
        )
        addQuestionWithRadioButtons(
            container,
            "Do you prefer spicy food?",
            arrayOf<String>("Yes", "No")
        )
        addQuestionWithRadioButtons(
            container,
            "Do you prefer vegetarian food?",
            arrayOf<String>("Yes", "No")
        )
        addQuestionWithRadioButtons(container, "Do you like seafood?", arrayOf<String>("Yes", "No"))
    }
    private fun addDietPreferenceQuestions(container: LinearLayout) {

        addQuestionWithRadioButtons(
            container,
            "Are you vegetarian?",
            arrayOf<String>("Yes", "No")
        )
        addQuestionWithRadioButtons(
            container,
            "Do you prefer organic food? ",
            arrayOf<String>("Never", "Rarely", "Sometimes", "Frequently")
        )
        addQuestionWithRadioButtons(
            container,
            "Do you consume dairy products?",
            arrayOf<String>("Yes", "No")
        )
        addQuestionWithRadioButtons(
            container,
            "Do you eat fast food?",
            arrayOf<String>("Yes", "No")
        )
        addQuestionWithRadioButtons(container, "Do you have any food allergies?", arrayOf<String>("Yes", "No"))
    }

    private fun addQuestionWithRadioButtons(
        container: LinearLayout,
        questionText: String,
        options: Array<String>
    ) {
        val questionTextView = TextView(this).apply {
            text = questionText
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 0)
            }
        }
        container.addView(questionTextView)

        val radioGroup = RadioGroup(this).apply {
            layoutParams = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
            )
            tag = questionText
        }

        options.forEach { option ->
            val radioButton = RadioButton(this).apply {
                text = option
            }
            radioGroup.addView(radioButton)
        }
        container.addView(radioGroup)
    }


    private fun showSurveyResults() {
        val allQuestionsContainer = findViewById<LinearLayout>(R.id.llQuestionsContainer)
        val results = StringBuilder()

        for (i in 0 until allQuestionsContainer.childCount) {
            val view = allQuestionsContainer.getChildAt(i)
            if (view is RadioGroup) {
                val questionText = view.tag.toString()
                val selectedId = view.checkedRadioButtonId
                if (selectedId != -1) {
                    val radioButton = findViewById<RadioButton>(selectedId)
                    val answerText = radioButton.text.toString()
                    results.append(questionText).append(" ").append(answerText).append("\n\n")
                }
            }
        }


        tvSurveyResults.text = results.toString().trim()
        tvSurveyResults.visibility = View.VISIBLE
    }

    private fun areAllQuestionsAnswered(): Boolean {
        val allQuestionsContainer = findViewById<LinearLayout>(R.id.llQuestionsContainer)
        for (i in 0 until allQuestionsContainer.childCount) {
            val view = allQuestionsContainer.getChildAt(i)
            if (view is RadioGroup) {
                if (view.checkedRadioButtonId == -1) {
                    return false
                }
            }
        }
        return true
    }

}