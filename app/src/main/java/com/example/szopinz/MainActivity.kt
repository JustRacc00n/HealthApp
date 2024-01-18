package com.example.szopinz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HealthRecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(HealthRecordViewModel::class.java)

        submitButton.setOnClickListener {
            val meal = mealInput.text.toString()
            val symptoms = symptomsInput.text.toString()
            val medication = medicationInput.text.toString()

            viewModel.updateHealthRecord(meal, symptoms, medication)
        }
    }
}

