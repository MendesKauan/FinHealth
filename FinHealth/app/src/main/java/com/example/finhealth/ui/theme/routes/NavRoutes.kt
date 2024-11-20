package com.example.finhealth.ui.theme.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finhealth.R
import com.example.finhealth.ui.theme.screens.GainOutlay.ModalRegisterGainOutlay
import com.example.finhealth.ui.theme.screens.MainScreen
import com.example.finhealth.ui.theme.screens.ScreenContent
import com.example.finhealth.ui.theme.screens.ScreenList
import com.example.finhealth.viewModel.GainOutlayViewModel


object ScreenRoutes {
    val ScreenOne = "ScreenOne"
    val ModalRegister = "ModalRegister"
    val ScreenRegisterList = "ScreenRegisterList"

    @Composable
    fun getScreenTitle(route: String?): String {
        return when (route) {
            ScreenOne -> stringResource(id = R.string.hello_name)
            ModalRegister -> stringResource(id = R.string.register_value)
            ScreenRegisterList -> stringResource(id = R.string.history_register)
            else -> stringResource(id = R.string.app_name)
        }
    }
}


@Composable
fun NavRoutes( navController: NavHostController ) {

    val viewModel: GainOutlayViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.ScreenOne
    ) {

        composable(ScreenRoutes.ScreenOne) { ScreenContent(viewModel) }
        composable(ScreenRoutes.ModalRegister) { ModalRegisterGainOutlay(navController, viewModel) } // preciso aqui
        composable(ScreenRoutes.ScreenRegisterList) { ScreenList() }

    }

}
