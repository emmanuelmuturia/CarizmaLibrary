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
package emmanuelmuturia.sonux.demo.activity

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
import emmanuelmuturia.sonux.demo.R
import emmanuelmuturia.sonux.demo.theme.SonuxTheme
import emmanuelmuturia.sonux.library.extensions.to8D
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SonuxTheme {
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

    var mediaPlayer = MediaPlayer.create(context, R.raw.rock)

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