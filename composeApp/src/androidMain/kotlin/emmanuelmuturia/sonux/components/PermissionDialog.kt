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
package emmanuelmuturia.sonux.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import emmanuelmuturia.sonux.permissions.PermissionAction
import emmanuelmuturia.sonux.permissions.checkIfPermissionIsGranted
import emmanuelmuturia.sonux.permissions.shouldShowPermissionRationale

@Composable
fun PermissionDialog(
    permission: String,
    permissionAction: (PermissionAction) -> Unit
) {

    val context = LocalContext.current
    val isPermissionGranted = checkIfPermissionIsGranted(context = context, permission = permission)
    if (isPermissionGranted) {
        permissionAction(PermissionAction.PermissionGranted)
        return
    }

    val permissionsLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            permissionAction(PermissionAction.PermissionGranted)
        } else {
            permissionAction(PermissionAction.PermissionDenied)
        }
    }

    val showPermissionRationale = shouldShowPermissionRationale(
        context = context,
        permission = permission
    )
    var isDialogMissed by remember { mutableStateOf(value = false) }
    var isPristine by remember { mutableStateOf(value = true) }

    if ((showPermissionRationale && !isDialogMissed) || (!isDialogMissed && !isPristine)) {
        isPristine = false
        AlertDialog(
            onDismissRequest = {
                isDialogMissed = true
                permissionAction(PermissionAction.PermissionDenied)
            },
            title = { Text(text = "Permission Required") },
            text = {
                Text(
                    text = "This app requires the Read Media (Audio) Permission to be granted..."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        isDialogMissed = true
                        permissionsLauncher.launch(input = permission)
                    }
                ) {
                    Text(text = "Grant Access")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isDialogMissed = true
                        permissionAction(PermissionAction.PermissionDenied)
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    } else {
        if (!isDialogMissed) {
            SideEffect {
                permissionsLauncher.launch(input = permission)
            }
        }
    }

}