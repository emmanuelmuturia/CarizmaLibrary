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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.sin


internal fun applyAutoPanning(
    mediaPlayer: MediaPlayer,
    frequency: Float = 0.08f,
    amount: Float = 85f
) {
    mediaPlayer.start()

    val panningJob = CoroutineScope(context = Dispatchers.Main).launch {
        var phase = 0.0

        while (isActive) {
            val leftVolume = ((1 - amount / 100) * sin(x = phase) + 1).toFloat() / 2
            val rightVolume = ((1 + amount / 100) * sin(x = phase) + 1).toFloat() / 2

            mediaPlayer.setVolume(leftVolume, rightVolume)

            phase += (2 * Math.PI * frequency) / 60

            delay(timeMillis = 16L)
        }
    }

    mediaPlayer.setOnCompletionListener {
        panningJob.cancel()
    }
}

internal fun applyReverb(mediaPlayer: MediaPlayer) {
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
    mediaPlayer.start()
}