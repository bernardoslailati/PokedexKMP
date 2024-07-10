package di

import common.network.service.PokemonApi
import data.repository.PokemonRepositoryImpl
import domain.repository.PokemonRepository
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
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.PokemonViewModel

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
    single<PokemonRepository> {
        PokemonRepositoryImpl(api = get())
    }
}

val viewModelModule = module {
    viewModelOf(::PokemonViewModel)
}

fun initKoin() {
    startKoin {
        modules(commonModule, viewModelModule)
    }
}