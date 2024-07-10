package data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SpritesApiModel(
    val other: OtherSpritesApiModel? = null
)