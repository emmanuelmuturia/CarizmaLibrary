/*
 * Copyright 2024 Carizma Library
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package emmanuelmuturia.carizmalibrary.ui

import android.media.MediaPlayer
import android.media.audiofx.EnvironmentalReverb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import emmanuelmuturia.carizmalibrary.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import kotlin.math.sin

@Composable
fun TestAudioEffect(modifier: Modifier = Modifier) {
    var isPlaying by remember { mutableStateOf(value = false) }

    val context = LocalContext.current
    var mediaPlayer = MediaPlayer.create(context, R.raw.solo)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {
            isPlaying = !isPlaying
            if (isPlaying) {
                // startPanning(mediaPlayer = mediaPlayer)
                applyReverb(mediaPlayer = mediaPlayer)
            } else {
                mediaPlayer?.release()
                mediaPlayer = null
            }
        }) {
            Icon(
                imageVector = if (isPlaying) Icons.Rounded.Clear else Icons.Rounded.PlayArrow,
                contentDescription = ""
            )
        }
    }
}

private fun applyAutoPanning(
    mediaPlayer: MediaPlayer,
    frequency: Float,
    amount: Float
) {

    val panningJob = CoroutineScope(context = Dispatchers.Main).launch {

        var phase = 0.0

        while (isActive) {

            val leftVolume = ((1 - amount / 100) * sin(x = phase) + 1).toFloat() / 2
            val rightVolume = ((1 + amount / 100) * sin(x = phase) + 1).toFloat() / 2

            mediaPlayer.setVolume(leftVolume, rightVolume)

            phase += (2 * Math.PI * frequency) / 60

            delay(timeMillis = 16L)

            mediaPlayer.start()

        }

    }

    mediaPlayer.setOnCompletionListener {
        panningJob.cancel()
    }

}

private fun applyReverb(mediaPlayer: MediaPlayer) {
    EnvironmentalReverb(0, mediaPlayer.audioSessionId).apply {
        enabled = true
        decayHFRatio = 0 // Extremely low decay ratio
        decayTime = 20000 // Maximum long decay time
        density = 1000 // Maximum density
        diffusion = 1000 // Maximum diffusion
        reverbLevel = 1000 // Maximum reverb effect
        reverbDelay = 1000 // Maximum reverb delay
    }
    /*val reverb = PresetReverb(1, 0).apply {
        enabled = true
        preset = PresetReverb.PRESET_LARGEHALL
    }
    mediaPlayer.attachAuxEffect(reverb.id)
    mediaPlayer.setAuxEffectSendLevel(1.0f)*/
    mediaPlayer.start()
}