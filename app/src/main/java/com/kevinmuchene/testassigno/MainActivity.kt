package com.kevinmuchene.testassigno

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var submitBtn: Button
    private lateinit var buttonDietaryHabits: Button
    private lateinit var surveyTypeGroup: RadioGroup

    private var selectedSurveyTypeId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        submitBtn = findViewById(R.id.submitBtnId)
//        buttonDietaryHabits = findViewById(R.id.dietPreferenceId)
        surveyTypeGroup = findViewById(R.id.surveyTypeGroupId)

        surveyTypeGroup.setOnCheckedChangeListener { group, checkedId ->
            selectedSurveyTypeId = checkedId
        }

        submitBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, SurveyActivity::class.java)
            when (selectedSurveyTypeId) {
                R.id.foodPreferenceId -> intent.putExtra("surveyType", "FoodPreferences")
                R.id.dietPreferenceId -> intent.putExtra("surveyType", "DietaryHabits")
                else -> {
                    Toast.makeText(this@MainActivity, "Please select an option", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            startActivity(intent)
        }



    }

}