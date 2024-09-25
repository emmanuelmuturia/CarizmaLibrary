package emmanuelmuturia.carizmalibrary.ui

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.datasource.AssetDataSource
import androidx.media3.datasource.DataSpec
import androidx.media3.exoplayer.ExoPlayer

@Composable
fun TestPanningEffect(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val exoplayer = ExoPlayer.Builder(context).build()
    val mediaItem = MediaItem.fromUri(Uri.parse("android.resource://${context.packageName}/raw/monza"))
    exoplayer.setMediaItem(mediaItem)

}