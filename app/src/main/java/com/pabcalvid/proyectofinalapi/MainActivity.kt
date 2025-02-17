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
import androidx.compose.ui.tooling.preview.Preview
import com.pabcalvid.proyectofinalapi.navigation.MainTopBar
import com.pabcalvid.proyectofinalapi.ui.theme.ProyectoFinalAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoFinalAPITheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize().background(Color.Black),
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

}
