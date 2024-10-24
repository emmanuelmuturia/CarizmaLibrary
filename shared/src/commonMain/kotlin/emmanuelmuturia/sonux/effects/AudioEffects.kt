package emmanuelmuturia.sonux.effects

import kotlinx.coroutines.CoroutineDispatcher

interface AudioEffects {

    suspend fun playAudioIn8D(
        audioFileUri: String,
        frequency: Float = 0.49f,
        amount: Float = 80f,
        coroutineDispatcher: CoroutineDispatcher
    )

    suspend fun applyAutoPanning(
        frequency: Float = 0.49f,
        amount: Float = 80f,
        coroutineDispatcher: CoroutineDispatcher
    )

    suspend fun applyReverb(
        coroutineDispatcher: CoroutineDispatcher
    )

    suspend fun stopPlayingAudio()
}

expect fun getAudioEffects(): AudioEffects