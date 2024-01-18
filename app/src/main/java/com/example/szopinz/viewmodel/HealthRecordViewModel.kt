package com.example.szopinz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class HealthRecordViewModel : ViewModel() {
    val healthRecord = MutableLiveData<HealthRecord>()

    fun updateHealthRecord(meal: String, symptoms: String, medication: String) {
        healthRecord.value = HealthRecord(meal, symptoms, medication)
    }
}

