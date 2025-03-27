package com.example.pedidos.data

import androidx.annotation.DrawableRes
import com.example.pedidos.R

data class Comida(
    val id: Int,
    val nombre: String,
    val precio: Double,
    @DrawableRes val imagenRes: Int
)

// Lista de ejemplo
val comidasEjemplo = listOf(
    Comida(1, "Pizza Margarita", 8.99, R.drawable.pizza),
    Comida(2, "Hamburguesa Clásica", 6.50, R.drawable.hamburguesa),
    Comida(3, "Sushi Variado", 12.99, R.drawable.sushi),
    Comida(4, "Pollo Frito", 7.50, R.drawable.pollo),
    Comida(5, "Papas Fritas", 9.99, R.drawable.papas),
    Comida(6, "Tarta de Chocolate", 10.50, R.drawable.pastel)
)
