package com.example.pedidos.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pedidos.ui.theme.LoginScreen
import com.example.pedidos.viewmodel.PedidoViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    val carritoViewModel: CarritoViewModel = viewModel()
    val pedidoViewModel: PedidoViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("menu") }
            )
        }
        composable("menu") {
            MenuScreen(navController, carritoViewModel) // Pasamos el ViewModel aquí
        }
        composable("carrito") {
            CarritoScreen(
                carritoViewModel = carritoViewModel,
                pedidoViewModel = pedidoViewModel
            )
        }
        composable("pedidos") {
            PedidosEnCursoScreen()
        }

    }
}
