package com.kevinmuchene.testassigno

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SurveyActivity : AppCompatActivity() {

    private lateinit var favoriteCusineRD: RadioGroup;
    private lateinit var eatOutRD: RadioGroup;
    private lateinit var spicyFoodIdRD: RadioGroup;
    private lateinit var vegeterianRD: RadioGroup;
    private lateinit var seaFoodRD: RadioGroup;
    private lateinit var submitPreferencesBtn: Button;

    private lateinit var spicyFoodSelction: TextView;
    private lateinit var eatOutSelction: TextView;
    private lateinit var vegeterianSelection: TextView;
    private lateinit var seaFoodSelection: TextView;
    private lateinit var cusineSelection: TextView;


    private lateinit var foodPreferencesLayout: LinearLayout
    private lateinit var dietaryHabitsLayout: LinearLayout
    private lateinit var toggleGroup: RadioGroup

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
        val questionTextView = TextView(this)
        questionTextView.text = questionText
        questionTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 0, 0, 5)
        questionTextView.layoutParams = layoutParams
        container.addView(questionTextView)
        val radioGroup = RadioGroup(this)
        radioGroup.layoutParams =
            RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
            )
        for (option in options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioGroup.addView(radioButton)
        }
        container.addView(radioGroup)
    }

}