package com.example.minhapokedex

import android.os.Bundle
import android.telecom.Call
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.volley.Response
import com.example.minhapokedex.data.PokeViewModel
import com.example.minhapokedex.model.PokeApi
import com.example.minhapokedex.model.RetrofitInstance
import com.example.minhapokedex.view.FavoritesScreen
import com.example.minhapokedex.view.Pokedex
import javax.security.auth.callback.Callback

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
        NavHost(navController, startDestination = "pokedex") {
            composable("pokedex") { Pokedex(navController) }
            composable("favorites") { FavoritesScreen(PokeViewModel()) }
        }
    }
}