package com.example.szopinz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.szopinz.model.HealthRecord

class HealthRecordViewModel : ViewModel() {
    val healthRecord = MutableLiveData<HealthRecord>()

    fun updateHealthRecord(meal: String, symptoms: String, medication: String) {
        healthRecord.value = HealthRecord(meal, symptoms, medication)
    }
}

