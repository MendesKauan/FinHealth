package com.example.finhealth.ui.theme.utils

import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finhealth.R
import com.example.finhealth.ui.theme.routes.ScreenRoutes

@Composable
fun navBar(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
    NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(ScreenRoutes.ScreenRegisterList)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_paid),
                        contentDescription = "Money icon",
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { }
            )

            NavigationBarItem(
                selected = true,
                onClick = {
                    navController.navigate(ScreenRoutes.ScreenOne)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "add",
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { }
            )

            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(ScreenRoutes.ModalRegister)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add",
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { }
            )
        }
    }
}

