package presentation.model

import domain.model.PokemonType

data class PokemonCardModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>
)
