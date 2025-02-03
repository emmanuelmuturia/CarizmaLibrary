package emmanuelmuturia.sonux.repository

import kotlinx.coroutines.CoroutineDispatcher

expect class SonuxRepository {
    suspend fun convertAndDownloadAudio(
        inputUri: String,
        frequency: Float = 0.49f,
        coroutineDispatcher: CoroutineDispatcher
    )
}

