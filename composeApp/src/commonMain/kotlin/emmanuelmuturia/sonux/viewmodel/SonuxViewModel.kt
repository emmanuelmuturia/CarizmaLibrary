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