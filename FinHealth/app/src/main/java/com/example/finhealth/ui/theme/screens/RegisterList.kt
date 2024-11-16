package com.example.finhealth.ui.theme.screens

import android.annotation.SuppressLint
import android.app.LauncherActivity.ListItem
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.finhealth.R
import com.example.finhealth.viewModel.GainOutlayViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenList(viewModel: GainOutlayViewModel = viewModel()) {

    val gainOutlays = viewModel.gainOutlays.collectAsState().value
    var editingOutlay by remember { mutableStateOf<GainOutlayModel?>(null) }

    Scaffold (
        content = { paddingValues ->
                RegisterList(
                    modifier = Modifier.padding(paddingValues),
                    gainOutlays = gainOutlays,
                    onEdit = { editingOutlay = it })
        }
    )

    editingOutlay?.let { outlay ->
        EditRegisterModal(
            gainOutlay = outlay,
            onDismiss = { editingOutlay = null }, // Fecha o modal
            onSave = { updatedOutlay ->
                viewModel.updateAndSaveGainOutlay(updatedOutlay)
                editingOutlay = null
            },
            onDelete = { outlayToDelete ->
                viewModel.deleteGainOutlay(outlayToDelete)
                editingOutlay = null
            }
        )
    }
}





@Composable
fun RegisterList(modifier: Modifier,
                 gainOutlays: List<GainOutlayModel>,
                 onEdit: (GainOutlayModel) -> Unit) {

    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp)
    ){
        items(gainOutlays) { register ->
            ListItem (
                headlineContent = { Text(register.description) },
                supportingContent = {
                    val sign = if (register.type) "+" else "-"
                    Text("$sign R$ ${register.value}")
                                    },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = if (register.type) R.drawable.gain_icon else R.drawable.expense_icon),
                        contentDescription = "Icon",
                        modifier = Modifier.size(30.dp),
                        tint = if (register.type) Color.Green else Color.Red
                    )
                },
                trailingContent = {
                    IconButton(onClick = { onEdit(register) }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit"
                        )
                    }
                }
            )

            if (register != gainOutlays.last()) {
                Divider()
            }
        }
    }
}

