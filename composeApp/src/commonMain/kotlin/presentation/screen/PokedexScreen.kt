package presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.PokemonViewModel
import presentation.component.PokemonCard

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PokedexScreen(modifier: Modifier = Modifier, viewModel: PokemonViewModel = koinViewModel()) {
    val pokemons = viewModel.pokemonsFlow.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(top = 16.dp).padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(pokemons.value) { pokemon ->
            PokemonCard(pokemon = pokemon)
        }
    }
}

@Composable
@Preview
fun PokedexScreenPreview() {
    PokedexScreen()
}