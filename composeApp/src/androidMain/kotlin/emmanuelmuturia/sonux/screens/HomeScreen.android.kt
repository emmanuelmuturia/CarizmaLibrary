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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import emmanuelmuturia.sonux.components.PermissionDialog
import emmanuelmuturia.sonux.permissions.PermissionAction

@Composable
internal actual fun HomeScreenButton() {
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
                navigator?.push(item = ConfirmationScreen(audioFileUri = Uri.parse(uri.toString()).toString()))
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
    val permission = if (Build.VERSION.SDK_INT > 32) Manifest.permission.READ_MEDIA_AUDIO else Manifest.permission.READ_EXTERNAL_STORAGE

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