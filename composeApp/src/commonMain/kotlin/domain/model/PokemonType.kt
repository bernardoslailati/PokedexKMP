package domain.model

import org.jetbrains.compose.resources.DrawableResource
import pokedexkmp.composeapp.generated.resources.Res
import pokedexkmp.composeapp.generated.resources.*

enum class PokemonType(val type: String, val drawableResource: DrawableResource) {
    NORMAL(type = "Normal", drawableResource = Res.drawable.bg_normal),
    FIGHTING(type = "Fighting", drawableResource = Res.drawable.bg_fighting),
    WATER(type = "Water", drawableResource = Res.drawable.bg_water),
    FIRE(type = "Fire", drawableResource = Res.drawable.bg_fire),
    GRASS(type = "Grass", drawableResource = Res.drawable.bg_grass),
    POISON(type = "Poison", drawableResource = Res.drawable.bg_poison),
    ELECTRIC(type = "Electric", drawableResource = Res.drawable.bg_electric),
    GROUND(type = "Ground", drawableResource = Res.drawable.bg_ground),
    FLYING(type = "Flying", drawableResource = Res.drawable.bg_flying),
    PSYCHIC(type = "Psychic", drawableResource = Res.drawable.bg_psychic),
    ROCK(type = "Rock", drawableResource = Res.drawable.bg_rock),
    ICE(type = "Ice", drawableResource = Res.drawable.bg_ice),
    BUG(type = "Bug", drawableResource = Res.drawable.bg_bug),
    DRAGON(type = "Dragon", drawableResource = Res.drawable.bg_dragon),
    GHOST(type = "Ghost", drawableResource = Res.drawable.bg_ghost),
    DARK(type = "Dark", drawableResource = Res.drawable.bg_dark),
    STEEL(type = "Steel", drawableResource = Res.drawable.bg_steel),
    FAIRY(type = "Fairy", drawableResource = Res.drawable.bg_fairy);

    companion object {
        fun fromType(type: String): PokemonType {
            return when (type) {
                "Normal" -> NORMAL
                "Fighting" -> FIGHTING
                "Water" -> WATER
                "Fire" -> FIRE
                "Grass" -> GRASS
                "Poison" -> POISON
                "Electric" -> ELECTRIC
                "Ground" -> GROUND
                "Flying" -> FLYING
                "Psychic" -> PSYCHIC
                "Rock" -> ROCK
                "Ice" -> ICE
                "Bug" -> BUG
                "Dragon" -> DRAGON
                "Ghost" -> GHOST
                "Dark" -> DARK
                "Steel" -> STEEL
                "Fairy" -> FAIRY
                else -> NORMAL
            }
        }
    }
}