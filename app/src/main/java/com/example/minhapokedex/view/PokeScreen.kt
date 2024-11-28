package com.example.minhapokedex.view

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.minhapokedex.data.FavCard
import com.example.minhapokedex.data.PokeViewModel
import com.example.minhapokedex.model.RetrofitInstance
import com.example.minhapokedex.model.Root
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun PokeScreen(navController: NavController) {
    var cardImg by remember { mutableStateOf("") }
    var pokemonId by remember { mutableStateOf("") }
    var pokemonName by remember { mutableStateOf("") }
    var showShareButton by remember { mutableStateOf(false) }
    val context = LocalContext.current


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        TextField(
            value = pokemonName,
            onValueChange = { pokemonName = it },
            label = { Text("Buscar Pokemon") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        Button(onClick = {
            val apiKey = "5f162919-e614-4f3a-9d80-d0c1a7bcf2e2"
            val query = "name:${pokemonName.trim()}"
            RetrofitInstance.api.getCards(apiKey, query).enqueue(object : Callback<Root>{
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    if (response.isSuccessful) {
                        val card = response.body()?.data?.firstOrNull()
                        if (card != null) {
                            pokemonId = card.id
                            cardImg = card.images.large ?: ""
                            showShareButton = cardImg.isNotEmpty()
                        }else {
                            Log.e("Pokedex", "Nenhum card encontrado.")
                        }
                    }else {
                        Log.e("Pokedex", "Erro na resposta da API: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<Root>, t: Throwable) {
                    Log.e("Pokedex", "Falha na chamada da API: ${t.message}")
                }
            })
        }) {
            Text("Buscar")
        }

        if (cardImg.isNotEmpty()) {
            Image(
                painter = rememberImagePainter(cardImg),
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )
            Button(onClick = {
                if (pokemonId.isNotEmpty() && pokemonName.isNotEmpty() && cardImg.isNotEmpty()) {
                    val favCard = FavCard(id = pokemonId, name = pokemonName, imageUrl = cardImg)
                    val viewModel = PokeViewModel()
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            viewModel.insert(favCard) // Usando o ViewModel para inserir o card favorito.
                            Log.d("Pokedex", "Card favoritado: $favCard")
                        } catch (e: Exception) {
                            Log.e("Pokedex", "Erro ao inserir card favorito: ${e.message}")
                        }
                    }
                } else {
                    Log.e("Pokedex", "Favoritar falhou: pokemonId ou pokemonName está vazio.")
                }
            }) {
                Text("Favoritar")
            }


            if (showShareButton) {
                Button(onClick = { shareImage(cardImg, context) }) {
                    Text("Compartilhar")
                }
            }
            Button(onClick = { navController.navigate("favorites") }) { // Navegação para Favoritos.
                Text("Ver Favoritos")
            }
        }
    }
}

fun shareImage(imageUrl: String, context: Context) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, imageUrl)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(intent, "Compartilhar imagem"))
}
