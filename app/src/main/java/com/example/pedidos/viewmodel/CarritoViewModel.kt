package com.example.pedidos.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
            _carrito[index].cantidad += 1 // Si ya existe, incrementamos la cantidad
        } else {
            _carrito.add(CarritoItem(comida, cantidad = 1)) // Si no existe, lo añadimos con cantidad 1
        }
    }

    // Función para eliminar un producto del carrito
    fun eliminarDelCarrito(comida: Comida) {
        _carrito.removeAll { it.comida.id == comida.id }
    }

    // Función para disminuir la cantidad
    fun disminuirCantidad(comida: Comida) {
        val itemExistente = _carrito.find { it.comida.id == comida.id }
        itemExistente?.let {
            if (it.cantidad > 1) {
                it.cantidad-- // Disminuimos la cantidad
            } else {
                eliminarDelCarrito(comida) // Si la cantidad es 1, eliminamos el item
            }
        }
    }

    // Función para calcular el total del carrito
    fun calcularTotal(): Double {
        return _carrito.sumOf { it.comida.precio * it.cantidad }
    }

    // Función para limpiar el carrito
    fun limpiarCarrito() {
        _carrito.clear()
    }
}


