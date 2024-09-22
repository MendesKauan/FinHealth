package com.example.finhealth.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finhealth.screens.GainOutlay.ModalRegisterGainOutlay
import com.example.finhealth.screens.MainScreen
import com.example.finhealth.screens.ScreenContent
import com.example.finhealth.screens.ScreenList


object ScreenRoutes {
    val ScreenOne = "ScreenOne"
    val ModalRegister = "ModalRegister"
    val ScreenRegisterList = "ScreenRegisterList"
}


@Composable
fun NavRoutes(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.ScreenOne
    ) {

        composable(ScreenRoutes.ScreenOne) { ScreenContent() }
        composable(ScreenRoutes.ModalRegister) { ModalRegisterGainOutlay() }
        composable(ScreenRoutes.ScreenRegisterList) { ScreenList() }

    }

}
