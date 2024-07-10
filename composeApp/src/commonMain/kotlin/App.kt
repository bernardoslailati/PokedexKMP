import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedexkmp.composeapp.generated.resources.Res
import pokedexkmp.composeapp.generated.resources.bg_pokedex
import presentation.screen.PokedexScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxSize().blur(1.dp, 1.dp).shadow(elevation = 4.dp),
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
                    PokedexScreen()
                }
            }
        }
    }
}