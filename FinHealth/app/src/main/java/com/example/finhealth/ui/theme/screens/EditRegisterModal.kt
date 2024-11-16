package com.example.finhealth.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel


@Composable
fun EditRegisterModal(
    gainOutlay: GainOutlayModel,
    onDismiss: () -> Unit,
    onSave: (GainOutlayModel) -> Unit,
    onDelete: (GainOutlayModel) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier =  Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Editar Registro",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                // Campo de edição de valor
                var value by remember { mutableStateOf(gainOutlay.value.toString()) }
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Valor") }
                )

                // Campo de edição de descrição
                var description by remember { mutableStateOf(gainOutlay.description) }
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") }
                )

                // RadioButton para selecionar tipo
                var type by remember { mutableStateOf(gainOutlay.type) }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = type, onClick = { type = true })
                        Text("Ganho")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = !type, onClick = { type = false })
                        Text("Despesa")
                    }
                }

                // Botões de ação
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { onDelete(gainOutlay) }) {
                        Text("Excluir")
                    }
                    Button(onClick = {
                        val updatedOutlay = gainOutlay.copy(
                            value = value.toDoubleOrNull() ?: 0.0,
                            description = description,
                            type = type
                        )
                        onSave(updatedOutlay)
                    }) {
                        Text("Salvar")
                    }
                }
            }
        }
    }
}
