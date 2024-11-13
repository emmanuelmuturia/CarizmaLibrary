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
package emmanuelmuturia.sonux.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import emmanuelmuturia.sonux.effects.AudioEffects
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SonuxViewModel(
    private val audioEffects: AudioEffects,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _isPlaying: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    fun onPlayPauseButtonClicked(audioFileUri: String) {
        if (_isPlaying.value) {
            stopAudio()
        } else {
            playAudio(audioFileUri = audioFileUri)
        }
    }

    private fun playAudio(audioFileUri: String) {
        _isPlaying.value = true
        viewModelScope.launch {
            audioEffects.playAudioIn8D(
                frequency = 1.0f,
                amount = 100f,
                coroutineDispatcher = coroutineDispatcher,
                audioFileUri = audioFileUri
            )
        }
    }

    private fun stopAudio() {
        _isPlaying.value = false
        viewModelScope.launch {
            audioEffects.stopPlayingAudio()
        }
    }
}