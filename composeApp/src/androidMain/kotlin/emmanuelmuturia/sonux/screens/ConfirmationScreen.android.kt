package emmanuelmuturia.sonux.screens

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
internal actual fun AudioFileDetails(uri: String) {
    val context = LocalContext.current
    val contentResolver = context.contentResolver

    var audioTitle by rememberSaveable { mutableStateOf(value = "") }
    var audioType by rememberSaveable { mutableStateOf(value = "") }
    var audioSize by rememberSaveable { mutableLongStateOf(value = 0L) }

    val projection = arrayOf(
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.SIZE,
        MediaStore.Audio.Media.DISPLAY_NAME,
    )

    val cursor: Cursor? = contentResolver.query(
        Uri.parse(uri),
        projection,
        null,
        null,
        null
    )

    cursor.use {
        if (it != null) {
            if (it.moveToFirst()) {

                // Size
                val sizeColumnIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)
                audioSize = it.getLong(sizeColumnIndex)

                // Display Name
                val displayColumnIndex = it.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)
                audioTitle = it.getString(displayColumnIndex)

                // MIME Type
                audioType = contentResolver.getType(Uri.parse(uri)).toString()

            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                    append(text = "Audio Title: ")
                }
                append(text = audioTitle)
            }
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                    append(text = "Audio Type: ")
                }
                append(text = audioType)
            }
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                    append(text = "Audio Size: ")
                }
                append(text = "${audioSize / 1024} KB")
            }
        )
    }
}