package com.example.pedidos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pedido::class], version = 1, exportSchema = false)
abstract class PedidoDatabase : RoomDatabase() {
    abstract fun pedidoDao(): PedidoDao

    companion object {
        @Volatile
        private var INSTANCE: PedidoDatabase? = null

        fun getDatabase(context: Context): PedidoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PedidoDatabase::class.java,
                    "pedidos_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
