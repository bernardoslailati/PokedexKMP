package di

import data.network.PokemonApi
import data.repository.PokedexRepositoryImpl
import domain.repository.PokedexRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.PokedexViewModel

val commonModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }
    single<PokemonApi> {
        PokemonApi(client = get())
    }
    single<PokedexRepository> {
        PokedexRepositoryImpl(api = get())
    }
}

val viewModelModule = module {
    viewModelOf(::PokedexViewModel)
}

fun initKoin() {
    startKoin {
        modules(commonModule, viewModelModule)
    }
}