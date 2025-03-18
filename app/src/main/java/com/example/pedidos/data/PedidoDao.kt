package com.example.pedidos.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PedidoDao {
    @Insert
    suspend fun insertarPedido(pedido: Pedido)

    @Query("SELECT * FROM pedidos WHERE estado = 'En curso'")
    suspend fun obtenerPedidosEnCurso(): List<Pedido>
}
