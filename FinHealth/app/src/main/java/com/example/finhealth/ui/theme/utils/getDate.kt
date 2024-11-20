package com.example.finhealth.ui.theme.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class getDate {
    fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(Date())
    }
}

