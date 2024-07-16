package presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import domain.model.PokemonModel
import getPlatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import pokedexkmp.composeapp.generated.resources.Res
import pokedexkmp.composeapp.generated.resources.bg_pokedex
import pokedexkmp.composeapp.generated.resources.compose_multiplatform
import presentation.PokedexUiState
import presentation.PokedexViewModel
import presentation.component.PokemonCard
import presentation.mapper.toPresentation
import presentation.model.PokemonCardModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PokedexScreen(
    viewModel: PokedexViewModel = koinViewModel()
) {
    PokedexScreen(uiState = viewModel.uiState)
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokedexScreen(
    modifier: Modifier = Modifier,
    uiState: PokedexUiState
) {
    var selectedPokemon by remember { mutableStateOf<PokemonModel?>(null) }
    var showDetails by remember {
        mutableStateOf(false)
    }
    val lazyListState = rememberLazyListState()

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
            AnimatedContent(
                showDetails,
                label = "Animated Content Pokemon Details"
            ) { targetState ->
                if (!targetState) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            modifier = Modifier.fillMaxSize().blur(1.dp, 1.dp)
                                .shadow(elevation = 4.dp),
                            painter = painterResource(resource = Res.drawable.bg_pokedex),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Pokedex Image"
                        )
                        Column {
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "Pokedex - from ${getPlatform().name}",
                                style = MaterialTheme.typography.body1.copy(
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            )
                            PokemonListScreen(
                                pokemonList = uiState.pokemons,
                                lazyListState = lazyListState,
                                onPokemonClick = { pokemon ->
                                    selectedPokemon = pokemon
                                    showDetails = true
                                },
                                sharedTransitionScope = this@SharedTransitionLayout,
                                animatedVisibilityScope = this@AnimatedContent
                            )
                        }
                    }
                } else {
                    selectedPokemon?.id?.let { pokemonId ->
                        PokemonDetailScreen(
                            pokemonId = pokemonId,
                            onBack = { showDetails = false },
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedVisibilityScope = this@AnimatedContent
                        )
                    }
                }
            }
        }
    }
}