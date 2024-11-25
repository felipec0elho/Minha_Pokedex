package com.example.minhapokedex.view

import android.content.Context
import android.content.Intent
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
import coil.compose.rememberImagePainter
import com.example.minhapokedex.model.RetrofitInstance
import com.example.minhapokedex.model.Root
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Pokedex() {
    var cardImg by remember { mutableStateOf("") }
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
                        cardImg = card?.images?.large ?: ""
                        showShareButton = cardImg.isNotEmpty()
                    }
                }
                override fun onFailure(call: Call<Root>, t: Throwable) {
                    // Handle failure
                }
            })
        }) {
            Text("Buscar")
        }

        if (cardImg.isNotEmpty()) {
            Image(
                painter = rememberImagePainter (cardImg),
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )
            if (showShareButton) {
                Button(onClick = { shareImage(cardImg, context) }) {
                    Text("Compartilhar")
                }
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
