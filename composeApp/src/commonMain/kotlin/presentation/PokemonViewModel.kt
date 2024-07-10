package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.PokemonModel
import domain.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository): ViewModel() {

    private val _pokemonsFlow = MutableStateFlow<List<PokemonModel>>(emptyList())
    val pokemonsFlow: StateFlow<List<PokemonModel>> = _pokemonsFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository.fetchPokemons().collect {
                _pokemonsFlow.value = it
            }
        }
    }

}