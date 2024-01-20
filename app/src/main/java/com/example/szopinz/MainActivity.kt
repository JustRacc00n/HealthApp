package com.example.szopinz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.szopinz.viewmodel.HealthRecordViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HealthRecordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[HealthRecordViewModel::class.java]

        val foodButton: Button = findViewById(R.id.foodButton)
        foodButton.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            startActivity(intent)
        }

        val symptomsButton: Button = findViewById(R.id.symptomsButton)
        symptomsButton.setOnClickListener {
            val intent = Intent(this, SymptomsActivity::class.java)
            startActivity(intent)
        }

        val medsButton: Button = findViewById(R.id.medsButton)
        medsButton.setOnClickListener {
            val intent = Intent(this, MedsActivity::class.java)
            startActivity(intent)
        }

        val statsButton: Button = findViewById(R.id.statsButton)
        statsButton.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }

    }
}


