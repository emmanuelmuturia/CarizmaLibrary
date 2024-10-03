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
package emmanuelmuturia.sonux.data.extensions

import android.media.MediaPlayer
import emmanuelmuturia.sonux.data.effects.applyAutoPanning
import emmanuelmuturia.sonux.data.effects.applyReverb
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