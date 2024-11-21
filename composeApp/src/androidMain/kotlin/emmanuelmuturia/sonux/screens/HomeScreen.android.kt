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

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import emmanuelmuturia.sonux.components.PermissionDialog
import emmanuelmuturia.sonux.permissions.PermissionAction
import emmanuelmuturia.sonux.viewmodel.SonuxViewModel

@Composable
internal actual fun HomeScreenButton(sonuxViewModel: SonuxViewModel) {
    val navigator = LocalNavigator.current

    // State to track if permission is granted
    var isPermissionGranted by remember { mutableStateOf(value = false) }
    var showPermissionDialog by remember { mutableStateOf(value = false) }

    // Launcher for picking audio files
    val pickAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                // Handle selected audio file URI
                navigator?.push(
                    item = ConfirmationScreen(
                        audioFileUri = Uri.parse(uri.toString()).toString(),
                        sonuxViewModel = sonuxViewModel
                    )
                )
            }
        }
    )

    // Function to handle the permission action
    val handlePermissionAction: (PermissionAction) -> Unit = { permissionAction ->
        when (permissionAction) {
            is PermissionAction.PermissionGranted -> {
                isPermissionGranted = true
                // If permission granted, proceed with file picker
                pickAudioLauncher.launch(arrayOf("audio/*"))
            }
            is PermissionAction.PermissionDenied -> {
                isPermissionGranted = false
                // Handle permission denial (could show a message)
            }
        }
    }

    // Check if permission is needed and show dialog if necessary
    val permission = if (Build.VERSION.SDK_INT > 32) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    ExtendedFloatingActionButton(
        onClick = {
            if (isPermissionGranted) {
                // If permission is already granted, launch the file picker
                pickAudioLauncher.launch(arrayOf("audio/*"))
            } else {
                // Show the permission dialog if permission is not yet granted
                showPermissionDialog = true
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

    // Show PermissionDialog if necessary
    if (showPermissionDialog) {
        PermissionDialog(
            permission = permission,
            permissionAction = { permissionAction ->
                handlePermissionAction(permissionAction)
                // Close the dialog after permission action is handled
                showPermissionDialog = false
            }
        )
    }
}