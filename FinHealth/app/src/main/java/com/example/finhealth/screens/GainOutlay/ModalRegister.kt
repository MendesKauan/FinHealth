package com.example.finhealth.screens.GainOutlay

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ModalRegisterGainOutlay() {
    Scaffold(
        content = {
            Box( modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CardRegisterGainOutlay()
                }

        }
    )
}



@Composable
fun CardRegisterGainOutlay() {

    val selectedState = remember { mutableStateOf("ganho") }
    val inputValue = remember { mutableStateOf("") }


        Box (
            modifier = Modifier.size(width = 300.dp, height = 250.dp),
            contentAlignment = Alignment.TopCenter
        ){
            Text(
                text = "Registre os valores",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
//
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedState.value == "ganho",
                            onClick = { selectedState.value = "ganho" })
                        Text(text = "Ganho")

                        RadioButton(
                            selected = selectedState.value == "despesa",
                            onClick = { selectedState.value = "despesa" })
                        Text(text = "Despesa")
                    }

                    OutlinedTextField(
                            value = inputValue.value,
                            onValueChange = { inputValue.value = it },
                            label = { Text("Insira o valor") },
                            placeholder = { Text("0.00") },
                            modifier = Modifier.fillMaxWidth(0.8f)
                    )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SendValueButton(onClick = { 1 + 1 })
                    Spacer(modifier = Modifier.width(20.dp))
                    CancelButton(onClick = { 1 + 1 })
                }

            }
        }

}


@Composable
fun SendValueButton(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text("Enviar")
    }
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text("Cancelar")
    }
}

