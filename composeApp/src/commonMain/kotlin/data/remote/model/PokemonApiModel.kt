package data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonApiModel(
    val id: Int? = null,
    val name: String? = null,
    val sprites: SpritesApiModel? = null,
    val types: List<PokemonTypesApiModel?>? = null,
)