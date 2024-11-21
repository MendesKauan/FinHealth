package com.example.finhealth.ui.theme.screens.GainOutlay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import com.example.finhealth.viewModel.GainOutlayViewModel

@Composable
fun CardValue(gainOutlays: List<GainOutlayModel>) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row {
            CardGain(gainOutlays = gainOutlays)
            CardOutlay(gainOutlays = gainOutlays)
        }
        CurrentBalance(gainOutlays = gainOutlays)
    }
}

@Composable
fun CardOutlay(gainOutlays: List<GainOutlayModel>) {
    val totalOutlay = gainOutlays.filter { !it.type }.sumOf { it.value }

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

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "R$ %.2f".format(totalOutlay),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

@Composable
fun CardGain(gainOutlays: List<GainOutlayModel>) {
    val totalGain = gainOutlays.filter { it.type }.sumOf { it.value }
    ElevatedCard(
        modifier = Modifier
            .size(width = 180.dp, height = 100.dp)
    ) {
        Box (
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Ganho",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "R$ %.2f".format(totalGain),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CurrentBalance(gainOutlays: List<GainOutlayModel>) {
    val totalGain = gainOutlays.filter { it.type }.sumOf { it.value }
    val totalOutlay = gainOutlays.filter { !it.type }.sumOf { it.value }
    val currentBalance = totalGain - totalOutlay

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 0.dp)
            .size(height = 46.dp, width = 0.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Saldo Atual: ",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "R$ %.2f".format(currentBalance),
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

        }
    }
}