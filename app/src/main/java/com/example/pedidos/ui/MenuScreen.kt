package com.example.pedidos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pedidos.data.Comida
import com.example.pedidos.data.comidasEjemplo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Menú de Comidas") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(comidasEjemplo) { comida ->
                ComidaItem(comida)
            }
        }
    }
}

@Composable
fun ComidaItem(comida: Comida) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Acción para detalles */ },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = comida.imagenRes),
                contentDescription = comida.nombre,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(comida.nombre, style = MaterialTheme.typography.bodyLarge)
                Text("Precio: $${comida.precio}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
