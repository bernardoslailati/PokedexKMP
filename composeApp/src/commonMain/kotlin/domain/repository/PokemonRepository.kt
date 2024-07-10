package domain.repository

import domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun fetchPokemons(): Flow<List<PokemonModel>>

}