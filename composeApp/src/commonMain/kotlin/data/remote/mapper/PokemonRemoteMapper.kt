package data.remote.mapper

import data.remote.model.PokemonApiModel
import domain.extension.orEmpty
import domain.extension.orZero
import domain.model.PokemonModel

fun PokemonApiModel.toDomain(): PokemonModel =
    PokemonModel(
        id = this.id.orZero(),
        name = this.name.orEmpty(),
        imageUrl = this.sprites?.other?.home?.frontDefault.orEmpty(),
        types = this.types?.mapNotNull { it?.type?.name.orEmpty() }.orEmpty(),
    )