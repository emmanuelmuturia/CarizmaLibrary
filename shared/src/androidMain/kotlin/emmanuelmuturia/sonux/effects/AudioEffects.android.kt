/*
 * Sonux  Copyright (C) 2024  Emmanuel Muturia™
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * The hypothetical commands `show w' and `show c' should show the appropriate
 * parts of the General Public License.  Of course, your program's commands
 * might be different; for a GUI interface, you would use an "about box".
 *
 * You should also get your employer (if you work as a programmer) or school,
 * if any, to sign a "copyright disclaimer" for the program, if necessary.
 * For more information on this, and how to apply and follow the GNU GPL, see
 * <https://www.gnu.org/licenses/>.
 *
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs.  If your program is a subroutine library, you
 * may consider it more useful to permit linking proprietary applications with
 * the library.  If this is what you want to do, use the GNU Lesser General
 * Public License instead of this License.  But first, please read
 * <https://www.gnu.org/licenses/why-not-lgpl.html>.
*/
package emmanuelmuturia.sonux.effects

import android.content.Context
import android.media.MediaPlayer
import android.media.audiofx.EnvironmentalReverb
import android.media.audiofx.PresetReverb
import android.net.Uri
import android.util.Log
import kotlin.math.sin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

actual fun getAudioEffects(): AudioEffects = AndroidAudioEffects()

class AndroidAudioEffects(
    private val mediaPlayer: MediaPlayer = MediaPlayer()
) : AudioEffects, KoinComponent {
    override suspend fun playAudioIn8D(
        audioFileUri: String,
        frequency: Float,
        amount: Float,
        coroutineDispatcher: CoroutineDispatcher
    ) {
        val mediaPlayer = MediaPlayer()

        withContext(coroutineDispatcher) {
            try {
                // Load an audio file into the media player
                val context: Context by inject()
                mediaPlayer.setDataSource(context, Uri.parse(audioFileUri))

                // Prepare the media player asynchronously
                mediaPlayer.prepare()

                // Use coroutineScope to run both effects concurrently
                coroutineScope {
                    // Start playing the audio
                    mediaPlayer.start()

                    // Launch AutoPanning Effect
                    launch {
                        var phase = 0.0

                        while (isActive) {
                            val leftVolume = (0.5f * (1 - amount / 100) * sin(phase) + 0.6f)
                                .coerceIn(0.3, 0.9)
                                .toFloat()
                            val rightVolume = (
                                0.5f * (1 + amount / 100) *
                                    sin(phase + Math.PI) + 0.6f
                                )
                                .coerceIn(0.3, 0.9)
                                .toFloat()

                            mediaPlayer.setVolume(leftVolume, rightVolume)
                            phase += (2 * Math.PI * frequency) / 60
                            delay(16L)
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
            } catch (e: Exception) {
                Log.e("playAudioIn8D", "Error playing audio: ${e.message}")
            }
        }
    }

    override suspend fun applyAutoPanning(
        frequency: Float,
        amount: Float,
        coroutineDispatcher: CoroutineDispatcher
    ) {
        val mediaPlayer = MediaPlayer()

        withContext(context = coroutineDispatcher) {
            var phase = 0.0

            while (isActive) {
                // Calculate the Left and Right Volumes using the Sine Wave, but with a smoother transition...
                val leftVolume =
                    (0.5f * (1 - amount / 100) * sin(x = phase) + 0.6f).coerceIn(
                        minimumValue = 0.3,
                        maximumValue = 0.9
                    ).toFloat()
                val rightVolume =
                    (0.5f * (1 + amount / 100) * sin(x = phase + Math.PI) + 0.6f).coerceIn(
                        minimumValue = 0.3,
                        maximumValue = 0.9
                    ).toFloat()

                // Apply the calculated Volumes to MediaPlayer...
                mediaPlayer.setVolume(leftVolume, rightVolume)

                // Progress the Phase for the next cycle, ensuring smooth Auto Panning...
                phase += (2 * Math.PI * frequency) / 60

                // Add a slight Delay for smoother transitions...
                delay(timeMillis = 16L)
            }
        }
    }

    override suspend fun applyReverb(coroutineDispatcher: CoroutineDispatcher) {
        val mediaPlayer = MediaPlayer()

        withContext(context = coroutineDispatcher) {
            EnvironmentalReverb(0, mediaPlayer.audioSessionId).apply {
                enabled = true
                roomLevel = 0 // This is the maximum room size based on a 100% Room Scale...
                roomHFLevel = -4500 // This is 50% HF Damping...
                decayTime = 10000 // This is 50% Reverberance (Decay Time in Milliseconds)...
                decayHFRatio = 1000 // This is the balanced Decay for Low and High Frequencies...
                reflectionsLevel = -2000 // This is a moderately early Reflection Level...
                reflectionsDelay = 0 // This is a 0 ms Pre-Delay...
                reverbLevel = 0 // This is a Wet Gain (Maximum Reverb Intensity)...
                reverbDelay = 0 // This is a 0 ms Delay before Reverberation starts...
                diffusion = 1000 // This is Maximum Stereo Depth (100%)...
                density = 1000 // Maximum Density (100%)
            }
        }
    }

    override suspend fun stopPlayingAudio() {
        mediaPlayer.stop()
    }
}