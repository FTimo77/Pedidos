package com.example.pedidos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pedidos.viewmodel.PedidoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidosEnCursoScreen(pedidoViewModel: PedidoViewModel = viewModel()) {
    val pedidos = pedidoViewModel.pedidosEnCurso

    LaunchedEffect(Unit) {
        pedidoViewModel.cargarPedidos()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pedidos en Curso") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (pedidos.isEmpty()) {
                Text("No hay pedidos en curso", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn {
                    items(pedidos) { pedido ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "Pedido #${pedido.id}")
                                Text(text = "Total: $${pedido.total}")
                                Text(text = "Fecha: ${java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(pedido.fecha)}")
                            }
                        }
                    }
                }
            }
        }
    }
}
