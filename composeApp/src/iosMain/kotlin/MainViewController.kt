import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin

object KoinInitializer {
    private var isKoinInitialized = false

    fun initKoinIfNeeded() {
        if (!isKoinInitialized) {
            initKoin()
            isKoinInitialized = true
        }
    }
}

fun MainViewController() = ComposeUIViewController {
    KoinInitializer.initKoinIfNeeded()
    App()
}