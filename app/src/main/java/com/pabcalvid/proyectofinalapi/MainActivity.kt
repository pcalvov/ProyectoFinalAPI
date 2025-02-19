package com.pabcalvid.proyectofinalapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { MainTopBar() }) { innerPadding ->
                    //Aplica el padding de la pantalla a la barra de navegación
                    Column(Modifier
                        .padding(innerPadding)
                        .fillMaxSize()) {
                        MainApp()
                    }
                }
            }
        }
    }
}

@Composable
fun MainApp(){
    val navHostController = rememberNavController()
    val remoteDatasource = RemoteDataSource(RetrofitBuilder.apiService)
    val localDatasource = LocalDataSource(LocalContext.current)
    val repository = MainRepository(localDatasource, remoteDatasource)
    val mainViewModel = ViewModel(repository)

    Navigation(navHostController, mainViewModel)
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    val navHostController = rememberNavController()
    val remoteDatasource = RemoteDataSource(RetrofitBuilder.apiService)
    val localDatasource = LocalDataSource(LocalContext.current)
    val repository = MainRepository(localDatasource, remoteDatasource)
    val mainViewModel = ViewModel(repository)

    Navigation(navHostController, mainViewModel)
}
