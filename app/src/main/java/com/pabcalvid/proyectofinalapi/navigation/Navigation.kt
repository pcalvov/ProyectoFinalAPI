package com.pabcalvid.proyectofinalapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pabcalvid.proyectofinalapi.ui.BooksScreen
import com.pabcalvid.proyectofinalapi.ui.MainScreen
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun Navigation(navController: NavHostController, mainViewModel: ViewModel) {
    NavHost(
        navController = navController,
        startDestination = Destinations.MainRoute.route,
    ) {
        composable(route = Destinations.MainRoute.route) {
            MainScreen(
                viewModel = mainViewModel,
                onList = { navController.navigate(Destinations.BookRoute.route) }
            )
        }

        composable(route = Destinations.BookRoute.route) {
            BooksScreen(
                viewModel = mainViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
