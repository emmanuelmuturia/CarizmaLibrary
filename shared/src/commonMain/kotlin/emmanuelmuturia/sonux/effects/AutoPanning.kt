package emmanuelmuturia.sonux.effects

import kotlinx.coroutines.CoroutineDispatcher

internal expect suspend fun applyAutoPanning(
    frequency: Float = 0.49f,
    amount: Float = 80f,
    coroutineDispatcher: CoroutineDispatcher
)