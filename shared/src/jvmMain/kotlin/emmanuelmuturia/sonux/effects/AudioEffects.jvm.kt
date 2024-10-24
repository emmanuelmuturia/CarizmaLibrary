package emmanuelmuturia.sonux.effects

import kotlinx.coroutines.CoroutineDispatcher

actual fun getAudioEffects(): AudioEffects = JVMAudioEffects()

class JVMAudioEffects : AudioEffects {
    override suspend fun playAudioIn8D(
        audioFileUri: String,
        frequency: Float,
        amount: Float,
        coroutineDispatcher: CoroutineDispatcher
    ) {
    }

    override suspend fun applyAutoPanning(
        frequency: Float,
        amount: Float,
        coroutineDispatcher: CoroutineDispatcher
    ) {
    }

    override suspend fun applyReverb(coroutineDispatcher: CoroutineDispatcher) {
    }

    override suspend fun stopPlayingAudio() {
        TODO("Not yet implemented")
    }
}