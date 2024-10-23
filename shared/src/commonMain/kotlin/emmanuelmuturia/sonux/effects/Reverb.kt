package emmanuelmuturia.sonux.effects

import kotlinx.coroutines.CoroutineDispatcher

internal expect suspend fun applyReverb(
    coroutineDispatcher: CoroutineDispatcher
)