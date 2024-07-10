import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.commonModule
import di.viewModelModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(commonModule, viewModelModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "PokedexKMP",
    ) {
        App()
    }
}