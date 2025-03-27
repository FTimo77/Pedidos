package com.example.pedidos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PedidoDao {
    @Insert
    suspend fun insertarPedido(pedido: Pedido): Long

    @Query("SELECT * FROM pedidos WHERE estado = 'En curso'")
    suspend fun obtenerPedidosEnCurso(): List<Pedido>

    @Delete
    suspend fun eliminarPedido(pedido: Pedido) // Asegúrate de que esta función existe
}





