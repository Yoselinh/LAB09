package com.example.lab09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab09.ui.theme.Lab09Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab09Theme {
                val navController = rememberNavController()
                val vm: ProductViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    //Pantalla de inicio
                    composable("home") {
                        HomeScreen(navController = navController)
                    }

                    // Pantalla del listado de productos
                    composable("products") {
                        ProductListScreen(
                            products = vm.productList,
                            navController = navController
                        )
                    }

                    //  Pantalla de detalle del producto
                    composable(
                        route = "detail/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id") ?: 0
                        vm.loadProductById(id)

                        ProductDetailScreen(
                            product = vm.selectedProduct.value,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}


