package com.pabcalvid.proyectofinalapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.pabcalvid.proyectofinalapi.data.MainRepository
import com.pabcalvid.proyectofinalapi.data.local.LocalDataSource
import com.pabcalvid.proyectofinalapi.data.remote.RemoteDataSource
import com.pabcalvid.proyectofinalapi.data.remote.RetrofitBuilder
import com.pabcalvid.proyectofinalapi.navigation.MainTopBar
import com.pabcalvid.proyectofinalapi.navigation.Navigation
import com.pabcalvid.proyectofinalapi.ui.theme.ProyectoFinalAPITheme
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoFinalAPITheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navHostController = rememberNavController()
    val remoteDatasource = RemoteDataSource(RetrofitBuilder.apiService)
    val localDatasource = LocalDataSource(LocalContext.current)
    val repository = MainRepository(localDatasource, remoteDatasource)
    val mainViewModel = ViewModel(repository)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar() },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { navHostController.navigate("favorites") }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favoritos")
                    }

                    IconButton(onClick = { navHostController.navigate("main") }) {
                        Icon(Icons.Filled.Home, contentDescription = "Inicio")
                    }

                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Navigation(navHostController, mainViewModel)
        }
    }
}
