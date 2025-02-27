package com.pabcalvid.proyectofinalapi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pabcalvid.proyectofinalapi.R

@Composable
fun MainScreen(
    onBooks: () -> Unit,
    onCharacters: () -> Unit,
    onHouses: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Bienvenido: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Yellow
        )

        Spacer(Modifier.size(16.dp))

        ImageButton(
            imageRes = R.drawable.libros,
            text = "Libros",
            onClick = onBooks
        )

        Spacer(Modifier.size(16.dp))

        ImageButton(
            imageRes = R.drawable.harry_potter,
            text = "Personajes",
            onClick = onCharacters
        )

        Spacer(Modifier.size(16.dp))

        ImageButton(
            imageRes = R.drawable.casas,
            text = "Casas",
            onClick = onHouses
        )
    }
}

@Composable
fun ImageButton(imageRes: Int, text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = text,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow
            )
        }
    }
}