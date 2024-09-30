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
import kotlin.math.sin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext

/**
 * This is the Auto Panning Effect that creates an immersive auditory experience by dynamically
 * shifting the Left Volume and the Right Volume of the audio...
 */

internal suspend fun applyAutoPanning(
    mediaPlayer: MediaPlayer,
    frequency: Float = 0.08f,
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

            // Add a slight delay for smoother transitions
            delay(timeMillis = 16L)
        }
    }
}