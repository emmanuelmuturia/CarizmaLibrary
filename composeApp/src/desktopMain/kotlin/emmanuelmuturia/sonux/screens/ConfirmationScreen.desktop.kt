/*
 * Sonux  Copyright (C) 2025  Emmanuel Muturia™
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
package emmanuelmuturia.sonux.screens

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Composable
internal actual fun AudioFileDetails(uri: String) {
    var audioTitle by rememberSaveable { mutableStateOf(value = "") }
    var audioType by rememberSaveable { mutableStateOf(value = "") }
    var audioSize by rememberSaveable { mutableLongStateOf(value = 0L) }

    try {
        val file = File(uri)
        if (file.exists() && file.isFile) {
            // Retrieve the Audio Title [file name without extension]...
            try {
                audioTitle = file.nameWithoutExtension
            } catch (e: Exception) {
                audioTitle = "Unknown Title"
                e.printStackTrace()
            }

            // Retrieve the MIME Type using Java NIO...
            try {
                val path = Paths.get(file.toURI())
                audioType = Files.probeContentType(path) ?: "Unknown Type"
            } catch (e: Exception) {
                audioType = "Unknown Type"
                e.printStackTrace()
            }

            // Retrieve the File Size...
            try {
                audioSize = file.length()
            } catch (e: Exception) {
                audioSize = 0L
                e.printStackTrace()
            }
        } else {
            audioTitle = "File does not exist"
            audioType = "N/A"
            audioSize = 0L
        }
    } catch (e: Exception) {
        audioTitle = "Error retrieving file details"
        audioType = "Error"
        audioSize = 0L
        e.printStackTrace()
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