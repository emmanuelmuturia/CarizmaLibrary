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
import android.media.audiofx.EnvironmentalReverb

/**
 * This is the Reverb Effect that creates the illusion of sound occurring in a specific space...
 */

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
}