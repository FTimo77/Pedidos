package com.example.pedidos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pedidos.data.Comida
import com.example.pedidos.data.comidasEjemplo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController, carritoViewModel: CarritoViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú de Comidas") },
                actions = {
                    IconButton(onClick = { navController.navigate("carrito") }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(comidasEjemplo) { comida ->
                ComidaItem(comida, carritoViewModel)
            }
        }
    }
}

@Composable
fun ComidaItem(comida: Comida, carritoViewModel: CarritoViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { carritoViewModel.agregarAlCarrito(comida) },
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
                Button(
                    onClick = { carritoViewModel.agregarAlCarrito(comida) },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Añadir al carrito")
                }
            }
        }
    }
}

