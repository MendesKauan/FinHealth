package com.example.finhealth.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.finhealth.screens.GainOutlay.CardValue

@Composable
fun ScreenContent() {
    Scaffold (
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 150.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardValue(gainValue = 1.000)

                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        elevation = CardDefaults.cardElevation(8.dp) // Sombra do card
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Últimos 5 Registros",
                                style = MaterialTheme.typography.titleMedium
                            )
                            RegisterList(
                                modifier = Modifier.fillMaxWidth(),
                                limit = 5
                            )
                        }
                    }
                }
            }
        }
    )
}