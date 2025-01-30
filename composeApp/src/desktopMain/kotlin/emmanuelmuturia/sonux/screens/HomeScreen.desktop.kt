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
package emmanuelmuturia.sonux.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import emmanuelmuturia.sonux.viewmodel.SonuxViewModel
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

@Composable
internal actual fun HomeScreenButton(sonuxViewModel: SonuxViewModel) {
    val navigator = LocalNavigator.current

    ExtendedFloatingActionButton(
        onClick = {
            val audioFileUri = openFilePicker()
            if (audioFileUri != null) {
                navigator?.push(
                    item = ConfirmationScreen(
                        audioFileUri = audioFileUri
                    )
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Select Audio File Button",
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Text(text = "Upload Audio File", modifier = Modifier.padding(all = 7.dp))
    }
}

internal fun openFilePicker(): String? {
    val fileDialog = FileDialog(Frame(), "Select an Audio File", FileDialog.LOAD)
    fileDialog.isVisible = true

    val selectedFile: File? = if (fileDialog.file != null) {
        File(fileDialog.directory, fileDialog.file)
    } else {
        null
    }

    return selectedFile?.absolutePath
}