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
package emmanuelmuturia.sonux.demo.extensions

import android.media.MediaPlayer
import emmanuelmuturia.sonux.demo.effects.applyAutoPanning
import emmanuelmuturia.sonux.demo.effects.applyReverb
import kotlinx.coroutines.Dispatchers

/**
 * These are the public-facing Extension Functions that trigger the respective Audio Effects...
 */

/**
 * This function combines [applyAutoPanning] and [applyReverb] to create an 8D Audio Effect...
 * @param mediaPlayer This is the [MediaPlayer] class that will be declared and used (Singleton Pattern) to control the audio and the Audio Effects...
 * @param frequency This is the Frequency of the Auto Panning Effect and it controls the speed in which the audio pans to both Left and Right...
 * @param amount This is used to control how far the audio moves between channels...
 */

suspend fun MediaPlayer.to8D(
    mediaPlayer: MediaPlayer,
    frequency: Float = 0.08f,
    amount: Float = 85f
) {
    val coroutineDispatcher = Dispatchers.Default

    applyReverb(mediaPlayer = mediaPlayer, coroutineDispatcher = coroutineDispatcher)
    mediaPlayer.start()
    applyAutoPanning(
        mediaPlayer = mediaPlayer,
        frequency = frequency,
        amount = amount,
        coroutineDispatcher = coroutineDispatcher
    )
    mediaPlayer.setOnCompletionListener {
        mediaPlayer.release()
    }
}