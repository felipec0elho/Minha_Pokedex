package com.example.minhapokedex.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.minhapokedex.data.FavCard
import com.example.minhapokedex.data.PokeViewModel

@Composable
fun CardItem(card: FavCard, context: Context, onRemove: (List<FavCard>) -> Unit) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = card.name)
        Image(
            painter = rememberImagePainter(card.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(viewModel: PokeViewModel) {
    val favlist by viewModel.favlist.collectAsState()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    // Usar um estado para armazenar os favoritos
    var favorites by remember { mutableStateOf(listOf<FavCard>()) }

    // Carregar os favoritos do banco de dados quando a tela for exibida
    LaunchedEffect(Unit) {
        viewModel.listarTodos()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Favoritos", modifier = Modifier.padding(16.dp))

        // Exibir os cards favoritos usando LazyColumn para uma lista rolável
        LazyColumn {
            items(favorites) { card ->
                CardItem(card, context) { updatedFavorites ->
                    favorites = updatedFavorites // Atualiza a lista de favoritos após remoção
                }
            }
        }
    }
}

