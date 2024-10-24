package emmanuelmuturia.sonux.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun PermissionDialog(
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