package presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import domain.extension.orEmpty
import domain.model.PokemonModel
import domain.model.PokemonType
import org.jetbrains.compose.resources.painterResource

@Composable
fun PokemonCard(modifier: Modifier = Modifier, pokemon: PokemonModel) {
    fun getAsyncImageLoader(context: PlatformContext) =
        ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).crossfade(true)
            .logger(DebugLogger()).build()

    val platformContext = LocalPlatformContext.current

    Card(
        modifier = modifier.fillMaxWidth(0.95f),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(
                    PokemonType.fromType(pokemon.types.firstOrNull()?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase() else it.toString()
                    }.orEmpty()).drawableResource
                ),
                contentDescription = "Pokemon Type Background Image",
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier.size(180.dp),
                    model = pokemon.imageUrl,
                    contentDescription = "Pokemon Image",
                    imageLoader = getAsyncImageLoader(platformContext)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "#${pokemon.id.toString().padStart(3, '0')}",
                        style = TextStyle.Default.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Default,
                            color = Color.White,
                            shadow = Shadow(offset = Offset(2f, 2f))
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = pokemon.name.uppercase(),
                        style = TextStyle.Default.copy(
                            fontSize = 20.sp,
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