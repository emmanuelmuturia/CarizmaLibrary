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

package emmanuelmuturia.carizmalibrary.data.effects

import android.media.MediaPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.sin

/**
 * This is the Auto Panning Effect that creates an immersive auditory experience by dynamically
 * shifting the Left Volume and the Right Volume of the audio...
 */

internal fun applyAutoPanning(
    mediaPlayer: MediaPlayer,
    frequency: Float = 0.08f,
    amount: Float = 85f
) {

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