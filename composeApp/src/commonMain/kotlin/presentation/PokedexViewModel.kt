package presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.PokemonModel
import domain.repository.PokedexRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(private val repository: PokedexRepository) : ViewModel() {

    private val _pokemonsFlow = MutableStateFlow<List<PokemonModel>>(emptyList())
    val pokemonsFlow: StateFlow<List<PokemonModel>> = _pokemonsFlow.asStateFlow()

    var uiState: PokedexUiState by mutableStateOf(PokedexUiState())

    init {
        viewModelScope.launch {
            repository.fetchPokemons().collect {
                _pokemonsFlow.value = it
                uiState = uiState.copy(pokemons = it)
            }
        }
    }

}

data class PokedexUiState(
    val pokemons: List<PokemonModel> = emptyList(),
)