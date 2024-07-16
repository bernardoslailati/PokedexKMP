import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.screen.PokedexScreen
import presentation.screen.PokemonDetailScreen

@Serializable
sealed interface AppRoute {
    @Serializable
    data object Pokedex : AppRoute

    @Serializable
    data class PokemonDetail(val pokemonId: Int) : AppRoute
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = AppRoute.Pokedex) {
            composable<AppRoute.Pokedex> {
                PokedexScreen(
//                    onPokemonDetails = { selectedPokemon ->
//                        navController.navigate(AppRoute.PokemonDetail(pokemonId = selectedPokemon.id))
//                    }
                )
            }
            composable<AppRoute.PokemonDetail> { backStackEntry ->
//                val pokemonDetail: AppRoute.PokemonDetail = backStackEntry.toRoute()
//                PokemonDetailScreen(
//                    pokemonId = pokemonDetail.pokemonId,
//                    onBack = { navController.popBackStack() }
//                )
            }
        }
    }
}