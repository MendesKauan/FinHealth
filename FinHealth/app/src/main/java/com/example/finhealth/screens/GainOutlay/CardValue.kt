package com.example.finhealth.screens.GainOutlay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CardValue(gainValue: Double) {
    Column {
        Row {
            CardGain(gainValue = 1.000)
            CardOutlay()
        }
    }
}

@Preview
@Composable
fun CardOutlay() {
    ElevatedCard(
        modifier = Modifier
            .size(width = 180.dp, height = 100.dp)

    ) {
        Box (
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Despesa",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun CardGain(gainValue: Double) {
    ElevatedCard(

        modifier = Modifier
            .size(width = 180.dp, height = 100.dp)
    ) {
        Box (
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Ganho: $gainValue",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

