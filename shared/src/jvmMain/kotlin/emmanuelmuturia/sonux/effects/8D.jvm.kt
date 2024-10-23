package emmanuelmuturia.sonux.effects

import kotlinx.coroutines.CoroutineDispatcher

internal actual suspend fun convertAudioTo8D(
    frequency: Float,
    amount: Float,
    coroutineDispatcher: CoroutineDispatcher
) {
}