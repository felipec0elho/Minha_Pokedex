package com.example.minhapokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minhapokedex.data.PokeViewModel
import com.example.minhapokedex.view.FavoritesScreen
import com.example.minhapokedex.view.PokeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    Pokedex()
                }
            }
        }
    }

    @Composable
    fun Pokedex() {
        val navController: NavHostController = rememberNavController()
        val viewModel = PokeViewModel()
        NavHost(navController = navController, startDestination = "PokeScreen") {
            composable("PokeScreen") { PokeScreen(navController) }
            composable("favorites") { FavoritesScreen(viewModel, navController) }
        }

//        NavHost(navController, startDestination = "PokeScreen") {
//            composable(
//                route = "PokeScreen"
//            ) {
//                PokeScreen(navController)
//            }
//            composable(
//                    route = "FavoritesScreen"
//                    ) {
//                FavoritesScreen(viewModel,navController)
//            }
//        }
    }
}
