package com.example.finhealth.ui.theme.screens.GainOutlay

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finhealth.R
import com.example.finhealth.data.ROOM.DataBaseROOM
import com.example.finhealth.data.models.GainOutlay.GainOutlayDao
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import com.example.finhealth.viewModel.GainOutlayViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun ModalRegisterGainOutlay(
    navController: NavHostController,
    viewModel: GainOutlayViewModel
) {

    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                title = {  // Adicione o título aqui
                    Text(
                        text = "Registrar Valores",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {  // Botão de voltar
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,  // Use o ícone de seta de voltar
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->

                Box( modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 200.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "Registre os valores",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    CardRegisterGainOutlay(viewModel = viewModel)
                }
        }
    )
}



@Composable
fun CardRegisterGainOutlay(viewModel: GainOutlayViewModel) {

    val selectedState = remember { mutableStateOf("ganho") }
    val inputValue = remember { mutableStateOf("") }
    val descriptionValue = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
//
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                Row (
                    modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = selectedState.value == "ganho",
                            onClick = { selectedState.value = "ganho" })
                        Text(
                            fontSize = 20.sp,
                            text = "Ganho"
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        ) {

                        RadioButton(
                            selected = selectedState.value == "despesa",
                            onClick = { selectedState.value = "despesa" })
                        Text(
                            fontSize = 20.sp,
                            text = "Despesa"
                        )

                    }
                }

                OutlinedTextField(
                    value = inputValue.value,
                    onValueChange = { inputValue.value = it },
                    label = { Text("Insira o valor") },
                    placeholder = { Text("R$ 0.00") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                OutlinedTextField(
                    value = descriptionValue.value,
                    onValueChange = { descriptionValue.value = it },
                    label = { Text("Insira a descrição") },
                    placeholder = { Text("compras no shopping...") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.4f)

                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SendValueButton(
                        onClick = {

                            val value = inputValue.value.toDoubleOrNull() ?: 0.0
                            val description = descriptionValue.value

                            val newGainOutlay = GainOutlayModel(
                                value = value,
                                description = description,
                                type = selectedState.value == "ganho"

                            )

                            viewModel.saveGainOutlay(newGainOutlay)
                    })

                }

            }
        }

}


@Composable
fun SendValueButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(0.8f),
        onClick = { onClick() },

    ) {
        Text("Adicionar +")
    }
}


