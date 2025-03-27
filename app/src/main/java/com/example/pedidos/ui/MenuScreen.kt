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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
    // Encontramos el item en el carrito, si existe
    val itemEnCarrito = carritoViewModel.carrito.find { it.comida.id == comida.id }

    // Recordamos el estado de la cantidad localmente en cada ítem
    var cantidad by remember { mutableStateOf(itemEnCarrito?.cantidad ?: 0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { carritoViewModel.agregarAlCarrito(comida) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = comida.imagenRes),
                contentDescription = comida.nombre,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(text = comida.nombre, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$${comida.precio}", style = MaterialTheme.typography.bodyMedium)
            }

            if (itemEnCarrito == null) {
                Button(onClick = {
                    carritoViewModel.agregarAlCarrito(comida)
                    cantidad += 1
                }) {
                    Text("Añadir")
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {
                        carritoViewModel.disminuirCantidad(comida)
                        if (cantidad > 0) cantidad-- // Actualizamos la cantidad localmente
                    }) {
                        Text("-")
                    }

                    // Mostrar la cantidad en la UI
                    Text(
                        text = cantidad.toString(),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Button(onClick = {
                        carritoViewModel.agregarAlCarrito(comida)
                        cantidad++ // Actualizamos la cantidad localmente
                    }) {
                        Text("+")
                    }
                }
            }
        }
    }
}



