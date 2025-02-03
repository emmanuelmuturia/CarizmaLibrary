package emmanuelmuturia.sonux.repository

import kotlinx.coroutines.CoroutineDispatcher

actual class SonuxRepository {
    actual suspend fun convertAndDownloadAudio(
        inputUri: String,
        frequency: Float,
        coroutineDispatcher: CoroutineDispatcher
    ) {
    }
}