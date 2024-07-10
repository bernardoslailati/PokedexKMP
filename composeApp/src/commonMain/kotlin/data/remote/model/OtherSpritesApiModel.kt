package data.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class OtherSpritesApiModel(
    val home: SpriteHomeApiModel? = null
)