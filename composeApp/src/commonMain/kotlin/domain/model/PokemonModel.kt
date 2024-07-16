package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>
)
