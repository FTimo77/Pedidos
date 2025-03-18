package com.example.pedidos.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pedidos.data.CarritoItem
import com.example.pedidos.data.Comida

class CarritoViewModel : ViewModel() {
    private val _carrito = mutableStateListOf<CarritoItem>()
    val carrito: List<CarritoItem> get() = _carrito

    // Función para agregar un producto al carrito
    fun agregarAlCarrito(comida: Comida) {
        val index = _carrito.indexOfFirst { it.comida.id == comida.id }
        if (index >= 0) {
            _carrito[index].cantidad += 1
        } else {
            _carrito.add(CarritoItem(comida))
        }
    }

    // Función para eliminar un producto del carrito
    fun eliminarDelCarrito(comida: Comida) {
        _carrito.removeAll { it.comida.id == comida.id }
    }

    fun calcularTotal(): Double {
        return _carrito.sumOf { it.comida.precio * it.cantidad }
    }

}
