package com.example.szopinz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class StatsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StatsScreen() // Your composable function
        }
    }
}

@Composable
fun StatsScreen(viewModel: StatsViewModel = viewModel()) {
    Column {
        var expanded by remember { mutableStateOf(false) }
        var selectedTable by remember { mutableStateOf("Symptoms") }

        Button(onClick = { expanded = true }) {
            Text(text = selectedTable)
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Symptoms") },
                onClick = {
                    selectedTable = "Symptoms"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Meds") },
                onClick = {
                    selectedTable = "Meds"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Food") },
                onClick = {
                    selectedTable = "Food"
                    expanded = false
                }
            )
        }

        // Tutaj wywołujesz funkcję z ViewModelu, która pobiera dane statystyczne
        // na podstawie wybranej tabeli.
        val stats = viewModel.getStatsForTable(selectedTable)

        // Wyświetlanie statystyk
        stats.forEach { stat ->
            Text("${stat.name}: ${stat.count}")
        }
    }
}

// W ViewModelu musisz zaimplementować logikę pobierania danych dla wybranej tabeli.
class StatsViewModel : ViewModel() {
    // Przykładowa metoda do pobierania danych statystycznych.
    // Musisz ją dostosować do swojej implementacji bazy danych.
    fun getStatsForTable(table: String): List<StatEntry> {
        // Tutaj powinno być zapytanie do bazy danych
        // Na razie zwracamy pustą listę dla przykładu
        return emptyList()
    }
}

// Reprezentacja wpisu statystycznego
data class StatEntry(val name: String, val count: Int)


