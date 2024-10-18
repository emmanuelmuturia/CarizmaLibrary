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
package emmanuelmuturia.sonux.demo.effects

import android.media.MediaPlayer
import kotlin.math.sin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext

/**
 * This is the Auto Panning Effect that creates an immersive auditory experience by dynamically
 * shifting the Left Volume and the Right Volume of the audio...
 *
 * @param mediaPlayer This is the [MediaPlayer] class that will be declared and used (Singleton Pattern) to control the audio and the Audio Effects...
 * @param frequency This is the Frequency of the Auto Panning Effect and it controls the speed in which the audio pans to both Left and Right...
 * @param amount This is used to control how far the audio moves between channels...
 * @param coroutineDispatcher This represents a [CoroutineDispatcher] instance that specifies on which Thread the Coroutine(s) will run on...
 */

internal suspend fun applyAutoPanning(
    mediaPlayer: MediaPlayer,
    frequency: Float = 0.49f,
    amount: Float = 80f,
    coroutineDispatcher: CoroutineDispatcher
) {
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