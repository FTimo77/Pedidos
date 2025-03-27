package com.example.pedidos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.pedidos.data.Pedido
import com.example.pedidos.data.PedidoDao
import com.example.pedidos.data.PedidoDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@SmallTest
class PedidoDaoTest {

    private lateinit var database: PedidoDatabase
    private lateinit var dao: PedidoDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PedidoDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.pedidoDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertPedido() = runTest {
        val pedido = Pedido(total = 20.0, fecha = System.currentTimeMillis(), estado = "En curso")
        dao.insertarPedido(pedido)

        val pedidos = dao.obtenerPedidosEnCurso()
        assertEquals(1, pedidos.size)
        assertEquals(pedido.total, pedidos[0].total, 0.01)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deletePedido() = runTest {
        val pedido = Pedido(total = 15.0, fecha = System.currentTimeMillis(), estado = "En curso")

        // Guarda el ID devuelto por insertarPedido
        val idInsertado = dao.insertarPedido(pedido)

        // Recupera el pedido exacto usando el ID insertado
        val pedidoGuardado = dao.obtenerPedidosEnCurso().first { it.id == idInsertado }

        // Elimina usando el mismo objeto que está en la base de datos
        dao.eliminarPedido(pedidoGuardado)

        val pedidos = dao.obtenerPedidosEnCurso()
        assertEquals(0, pedidos.size) // Ahora debería funcionar
    }



}