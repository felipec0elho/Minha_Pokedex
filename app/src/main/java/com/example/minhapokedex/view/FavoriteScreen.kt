package com.example.minhapokedex.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.minhapokedex.data.FavCard
import com.example.minhapokedex.data.PokeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(viewModel: PokeViewModel, navController: NavController) {
    val context = LocalContext.current

    // Usar um estado para armazenar os favoritos coletados do ViewModel.
    val favorites by viewModel.favlist.collectAsState()

    LaunchedEffect(favorites) {
        println("Favoritos atualizados: ${favorites.size}")
    }
    LaunchedEffect(favorites) {
        viewModel.listarTodos()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(text = "Favoritos", modifier = Modifier.padding(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f) // Permite que a lista ocupe o espaço restante
        ) {
            items(favorites, key = { it.id }) { card -> // Use um identificador único
                CardItem(card = card, context = LocalContext.current, viewModel = viewModel)
            }
        }
        }
    Button(
        onClick = { navController.navigate("PokeScreen") },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Procurar novo card para favoritar")
    }

}


@Composable
fun CardItem(card: FavCard, context: Context, viewModel: PokeViewModel) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = card.name)
        Image(
            painter = rememberImagePainter(card.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        // Botão para remover o card dos favoritos.
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.deletar(card) // Chama o método deletar no ViewModel.
            }
        }) {
            Text("Remover")
        }
    }
}