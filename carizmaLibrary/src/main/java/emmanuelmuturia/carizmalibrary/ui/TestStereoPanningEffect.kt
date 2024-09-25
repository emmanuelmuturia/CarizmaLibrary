package emmanuelmuturia.carizmalibrary.ui

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.exoplayer.ExoPlayer
import emmanuelmuturia.carizmalibrary.R
import java.util.Timer
import java.util.TimerTask

@Composable
fun TestStereoPanningEffect(modifier: Modifier = Modifier) {

    var isPlaying by remember { mutableStateOf(value = false) }

    val context = LocalContext.current
    /*val uri: Uri = Uri.parse("android.resource://${context.packageName}/raw/monza")
    val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            android.media.AudioAttributes.Builder()
                .setContentType(android.media.AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(android.media.AudioAttributes.USAGE_MEDIA)
                .build()
        )
        setDataSource(context, uri)
        prepare()
        start()
    }*/
    var mediaPlayer = MediaPlayer.create(context, R.raw.monza)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {
            isPlaying = !isPlaying
            if (isPlaying) {
                mediaPlayer.start()
                startPanning(mediaPlayer = mediaPlayer)
            } else {
                mediaPlayer?.release()
                mediaPlayer = null
            }
        }) {
            Icon(imageVector = if (isPlaying) Icons.Rounded.Clear else Icons.Rounded.PlayArrow, contentDescription = "")
        }
    }

}

private fun applyStereoPanning(
    pan: Float,
    mediaPlayer: MediaPlayer
) {
    val leftVolume = if(pan < 0) 1f + pan else 1f
    val rightVolume = if(pan > 0) 1f - pan else 1f
    mediaPlayer.setVolume(leftVolume, rightVolume)
}

private fun startPanning(mediaPlayer: MediaPlayer) {
    var currentPan = -1f
    val panSpeed = 0.01f
    Timer().schedule(object :TimerTask() {
        override fun run() {
            currentPan += panSpeed
            if (currentPan > 1f || currentPan < -1f) {
                currentPan = -1f
            }
            applyStereoPanning(pan = currentPan, mediaPlayer = mediaPlayer)
        }
    }, 0, 50)
}