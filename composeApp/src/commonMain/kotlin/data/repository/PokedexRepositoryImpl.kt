package data.repository

import data.network.PokemonApi
import data.network.PokemonApi.Companion.TOTAL_FIRST_GENERATION_POKEMONS
import data.remote.mapper.toDomain
import domain.model.PokemonModel
import domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokedexRepositoryImpl(private val api: PokemonApi) : PokedexRepository {

    override suspend fun fetchPokemons(): Flow<List<PokemonModel>> = flow {
        try {
            val pokemonList = mutableListOf<PokemonModel>()
            repeat(TOTAL_FIRST_GENERATION_POKEMONS) {
                val pokemon = api.fetchPokemon(it + 1)
                pokemonList.add(pokemon.toDomain())
                emit(pokemonList.toList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

}