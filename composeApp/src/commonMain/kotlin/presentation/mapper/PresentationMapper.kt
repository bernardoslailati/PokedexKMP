package presentation.mapper

import domain.extension.orEmpty
import domain.model.PokemonModel
import domain.model.PokemonType
import presentation.model.PokemonCardModel

fun PokemonModel.toPresentation(): PokemonCardModel {
    return PokemonCardModel(
        id = "#${id.toString().padStart(3, '0')}",
        name = name,
        imageUrl = imageUrl,
        types = types.map { typeString ->
            PokemonType.fromType(
                type = typeString.replaceFirstChar { type ->
                    if (type.isLowerCase())
                        type.titlecase()
                    else type.toString()
                }.orEmpty())
        }
    )
}