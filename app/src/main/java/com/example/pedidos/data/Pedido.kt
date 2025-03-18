package com.example.pedidos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class Pedido(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val total: Double,
    val fecha: Long, // Guardamos el timestamp
    val estado: String = "En curso"
)
