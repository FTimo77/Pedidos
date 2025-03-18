package com.example.pedidos.data

data class CarritoItem(
    val comida: Comida,
    var cantidad: Int = 1 // Por defecto, la cantidad es 1
)
