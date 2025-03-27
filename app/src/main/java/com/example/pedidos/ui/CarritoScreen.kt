package com.example.pedidos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.pedidos.viewmodel.PedidoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(carritoViewModel: CarritoViewModel, pedidoViewModel: PedidoViewModel) {
    // Estado para controlar la visibilidad del diálogo
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Carrito de Compras") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            val carrito = carritoViewModel.carrito
            val total = carritoViewModel.calcularTotal()

            if (carrito.isEmpty()) {
                Text(
                    text = "Tu carrito está vacío",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                // 📌 Lista de productos con espacio adaptable
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)  // ✅ Evita que el total y el botón sean desplazados
                        .fillMaxWidth()
                ) {
                    items(carrito) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = item.comida.imagenRes),
                                    contentDescription = item.comida.nombre,
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
                                    Text(text = item.comida.nombre)
                                    Text(text = "Cantidad: ${item.cantidad}")
                                    Text(text = "Precio: $${item.comida.precio}")
                                }

                                Button(
                                    onClick = { carritoViewModel.eliminarDelCarrito(item.comida) },
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }

                // 📌 Sección de Total y Botón (siempre visible)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Total: $${"%.2f".format(total)}",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Button(
                        onClick = {
                            // Mostrar el diálogo al hacer clic en "Confirmar Pedido"
                            showDialog = true
                            pedidoViewModel.guardarPedido(total)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Confirmar Pedido")
                    }
                }
            }
        }
    }

    // Mostrar el diálogo si showDialog es true
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                // Ocultar el diálogo al hacer clic fuera de él
                showDialog = false
            },
            title = { Text("Pedido Confirmado") },
            text = { Text("Tu pedido ha sido confirmado.") },
            confirmButton = {
                Button(onClick = {
                    // Ocultar el diálogo al hacer clic en "Aceptar"
                    showDialog = false
                    // Limpiar el carrito
                    carritoViewModel.limpiarCarrito()
                }) {
                    Text("Aceptar")
                }
            }
        )
    }
}