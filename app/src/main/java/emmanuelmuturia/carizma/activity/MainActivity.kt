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
package emmanuelmuturia.carizma.activity

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import emmanuelmuturia.carizmalibrary.R
import emmanuelmuturia.carizma.theme.CarizmaLibraryTheme
import emmanuelmuturia.carizmalibrary.data.extensions.to8D
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarizmaLibraryTheme {
                Scaffold { paddingValues ->
                    TestAudioEffects(
                        modifier = Modifier.padding(paddingValues = paddingValues),
                        context = this
                    )
                }
            }
        }
    }
}

@Composable
fun TestAudioEffects(modifier: Modifier = Modifier, context: Context) {
    var isPlaying by remember { mutableStateOf(value = false) }

    var mediaPlayer = MediaPlayer.create(context, R.raw.solo)

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {
            isPlaying = !isPlaying
            if (isPlaying) {
                coroutineScope.launch {
                    mediaPlayer.to8D(
                        mediaPlayer = mediaPlayer
                    )
                }
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