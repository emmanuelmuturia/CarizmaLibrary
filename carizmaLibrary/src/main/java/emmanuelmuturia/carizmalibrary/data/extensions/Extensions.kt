package emmanuelmuturia.carizmalibrary.data.extensions

import android.media.MediaPlayer
import emmanuelmuturia.carizmalibrary.data.effects.applyAutoPanning
import emmanuelmuturia.carizmalibrary.data.effects.applyReverb

/**
 * These are the public-facing Extension Functions that trigger the respective Audio Effects...
 */

/**
 * This function combines [applyAutoPanning] and [applyReverb] to create an 8D Audio Effect...
 */

fun MediaPlayer.to8D(
    mediaPlayer: MediaPlayer,
    frequency: Float = 0.08f,
    amount: Float = 85f
) {
    applyReverb(mediaPlayer = mediaPlayer)
    mediaPlayer.start()
    applyAutoPanning(mediaPlayer = mediaPlayer, frequency = frequency, amount = amount)
    mediaPlayer.setOnCompletionListener {
        mediaPlayer.release()
    }
}