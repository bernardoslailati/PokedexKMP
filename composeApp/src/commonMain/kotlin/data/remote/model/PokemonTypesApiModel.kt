package data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypesApiModel(
    val type: TypeApiModel? = null
)