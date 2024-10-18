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
import android.media.audiofx.EnvironmentalReverb
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * This is the Reverb Effect that creates the illusion of sound occurring in a specific space...
 * @param mediaPlayer This is the [MediaPlayer] class that will be declared and used (Singleton Pattern) to control the audio and the Audio Effects...
 * @param coroutineDispatcher This represents a [CoroutineDispatcher] instance that specifies on which Thread the Coroutine(s) will run on...
 */

internal suspend fun applyReverb(
    mediaPlayer: MediaPlayer,
    coroutineDispatcher: CoroutineDispatcher
) {
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