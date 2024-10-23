package emmanuelmuturia.sonux.effects

import android.media.MediaPlayer
import android.media.audiofx.EnvironmentalReverb
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.sin

internal actual suspend fun convertAudioTo8D(
    frequency: Float,
    amount: Float,
    coroutineDispatcher: CoroutineDispatcher
) {
    val mediaPlayer = MediaPlayer() // Common MediaPlayer instance for both effects

    withContext(context = coroutineDispatcher) {
        // Use coroutineScope to run both effects concurrently
        coroutineScope {
            // Launch AutoPanning Effect
            launch {
                var phase = 0.0

                while (isActive) {
                    // Calculate the Left and Right Volumes using the Sine Wave...
                    val leftVolume = (0.5f * (1 - amount / 100) * sin(x = phase) + 0.6f).coerceIn(
                        minimumValue = 0.3,
                        maximumValue = 0.9
                    ).toFloat()
                    val rightVolume = (0.5f * (1 + amount / 100) * sin(x = phase + Math.PI) + 0.6f).coerceIn(
                        minimumValue = 0.3,
                        maximumValue = 0.9
                    ).toFloat()

                    // Apply the calculated Volumes to MediaPlayer
                    mediaPlayer.setVolume(leftVolume, rightVolume)

                    // Progress the Phase for the next cycle
                    phase += (2 * Math.PI * frequency) / 60

                    // Add a slight delay for smoother transitions
                    delay(timeMillis = 16L)
                }
            }

            // Launch Reverb Effect
            launch {
                EnvironmentalReverb(0, mediaPlayer.audioSessionId).apply {
                    enabled = true
                    roomLevel = 0
                    roomHFLevel = -4500
                    decayTime = 10000
                    decayHFRatio = 1000
                    reflectionsLevel = -2000
                    reflectionsDelay = 0
                    reverbLevel = 0
                    reverbDelay = 0
                    diffusion = 1000
                    density = 1000
                }
            }
        }
    }
}