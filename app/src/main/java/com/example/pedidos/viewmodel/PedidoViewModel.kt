package com.example.pedidos.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pedidos.data.Pedido
import com.example.pedidos.data.PedidoDatabase
import kotlinx.coroutines.launch

class PedidoViewModel(application: Application) : AndroidViewModel(application) {
    private val pedidoDao = PedidoDatabase.getDatabase(application).pedidoDao()

    val pedidosEnCurso = mutableStateListOf<Pedido>()

    fun cargarPedidos() {
        viewModelScope.launch {
            pedidosEnCurso.clear()
            pedidosEnCurso.addAll(pedidoDao.obtenerPedidosEnCurso())
        }
    }

    fun guardarPedido(total: Double) {
        viewModelScope.launch {
            val pedido = Pedido(total = total, fecha = System.currentTimeMillis())
            pedidoDao.insertarPedido(pedido)
            cargarPedidos() // Refresca la lista
        }
    }
}
