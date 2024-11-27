package com.example.minhapokedex.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minhapokedex.PokeApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.minhapokedex.model.FavCardDAO
import com.example.minhapokedex.data.FavCard

class PokeViewModel(application: PokeApplication) : ViewModel() {
    private val favCardDAO = application.database.favCardDAO()
    private val _favList = MutableStateFlow<List<FavCard>>(emptyList())
    val favlist : StateFlow<List<FavCard>> = _favList

    init {
        listarTodos()
    }

    // Construtor padrão necessário para ViewModelProvider
    @Suppress("unused")
    constructor() : this(PokeApplication.instance)

    fun listarTodos() {
        viewModelScope.launch {
            _favList.value = favCardDAO.getAllFavorites()
        }
    }

    fun insert (favCard: FavCard) {
        viewModelScope.launch {
            favCardDAO.insert(favCard)
            listarTodos()
        }
    }

    fun atualizar(favCard: FavCard) {
        viewModelScope.launch {
            favCardDAO.atualizar(favCard) // Atualiza o contato no banco de dados
            listarTodos() // Recarrega a lista
        }
    }

    fun deletar(favCard: FavCard) {
        viewModelScope.launch {
            favCardDAO.delete(favCard)
            listarTodos()
        }
    }
}