package com.example.finhealth.screens

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finhealth.R


data class ListRegister (
    var type : Boolean = true,
    var descriptionRegister : String = "",
    var valueRegister : Double = 0.0,
)

val Registers: MutableList<ListRegister> = mutableListOf(
    ListRegister(true, "Salario", 1000.00),
    ListRegister(false, "McDonald's", 300.00),
    ListRegister(true, "Presente de aniversario", 550.00),
    ListRegister(false, "Mercado", 150.00),
    ListRegister(false, "Conta de luz", 120.00),
    ListRegister(false, "Aluguel", 800.00),
    ListRegister(true, "Freelance", 500.00),
    ListRegister(false, "Cinema", 50.00),
    ListRegister(false, "Restaurante", 200.00),
    ListRegister(true, "Venda de eletrônico usado", 300.00),
    ListRegister(false, "Manutenção do carro", 450.00),
    ListRegister(true, "Cashback do cartão de crédito", 25.00),
    ListRegister(false, "Farmácia", 80.00),
    ListRegister(true, "Bônus do trabalho", 700.00)
    )

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenList() {
    Scaffold (
        content = { paddingValues ->
                RegisterList(modifier = Modifier.padding(paddingValues) )
        }
    )

}



@Composable
fun RegisterList(modifier: Modifier, limit: Int? = null) {

    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(top = 65.dp)
    ){
        items(Registers.take(limit ?: Registers.size)) { register ->
            ListItem (
                headlineContent = { Text(register.descriptionRegister) },
                supportingContent = {
                    val sign = when (register.type) {
                        true -> "+"
                        false -> "-"
                    }

                    Text("$sign R$ ${register.valueRegister}")
                                    },
                leadingContent = {
                    when(register.type) {
                        true -> Icon(
                            painter = painterResource(id = R.drawable.gain_icon),
                            contentDescription = "Money icon",
                            modifier = Modifier.size(30.dp),
                            tint = androidx.compose.ui.graphics.Color.Green
                        )

                        false -> Icon(
                            painter = painterResource(id = R.drawable.expense_icon),
                            contentDescription = "Money icon",
                            modifier = Modifier.size(30.dp),
                            tint = androidx.compose.ui.graphics.Color.Red
                        )
                    }
                }
            )

            if (register != Registers.last()) {
                Divider()
            }
        }
    }
}

