package domain.model

data class PokemonModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>
)
