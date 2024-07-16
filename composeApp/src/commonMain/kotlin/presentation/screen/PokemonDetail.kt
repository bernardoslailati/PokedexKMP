package presentation.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import pokedexkmp.composeapp.generated.resources.Res
import pokedexkmp.composeapp.generated.resources.compose_multiplatform
import presentation.PokedexViewModel
import presentation.mapper.toPresentation
import presentation.model.PokemonCardModel


@OptIn(KoinExperimentalAPI::class, ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonDetailScreen(
    viewModel: PokedexViewModel = koinViewModel(),
    pokemonId: Int,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    viewModel.uiState.pokemons.firstOrNull { it.id == pokemonId }?.let {
        PokemonDetailScreen(
            selectedPokemon = it.toPresentation(),
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            onBack = onBack,
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    selectedPokemon: PokemonCardModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBack: () -> Unit
) {
    fun getAsyncImageLoader(context: PlatformContext) =
        ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).crossfade(true)
            .logger(DebugLogger()).build()

    val platformContext = LocalPlatformContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        with(sharedTransitionScope) {
            Box(modifier = Modifier.fillMaxSize()) {
                IconButton(
                    modifier = Modifier.align(Alignment.TopStart)
                        .zIndex(1f)
                        .padding(16.dp),
                    onClick = onBack
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(
                        selectedPokemon.types.firstOrNull()?.drawableResource
                            ?: Res.drawable.compose_multiplatform
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Pokemon type background"
                )
                Column(
                    modifier = Modifier.fillMaxSize().align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth()
                            .sharedBounds(
                                rememberSharedContentState(key = "${selectedPokemon.id}/id"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        text = selectedPokemon.id,
                        textAlign = TextAlign.Center,
                        style = TextStyle.Default.copy(
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Default,
                            color = Color.White,
                            shadow = Shadow(offset = Offset(2f, 2f))
                        )
                    )
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(0.95f)
                            .sharedBounds(
                                rememberSharedContentState(key = "${selectedPokemon.id}/image"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        model = selectedPokemon.imageUrl,
                        contentDescription = "Pokemon Image",
                        imageLoader = getAsyncImageLoader(
                            platformContext
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth()
                            .sharedBounds(
                                rememberSharedContentState(key = "${selectedPokemon.id}/name"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        text = selectedPokemon.name.uppercase(),
                        textAlign = TextAlign.Center,
                        style = TextStyle.Default.copy(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Default,
                            color = Color.White,
                            shadow = Shadow(offset = Offset(2f, 2f))
                        )
                    )
                }
            }
        }
    }
}