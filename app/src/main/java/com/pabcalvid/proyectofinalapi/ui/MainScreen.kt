package com.pabcalvid.proyectofinalapi.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

@Composable
fun MainScreen(viewModel: ViewModel, onList: () -> Unit){
    val book by viewModel.book
}