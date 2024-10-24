package emmanuelmuturia.sonux.components

import androidx.compose.runtime.Composable
import emmanuelmuturia.sonux.permissions.PermissionAction

@Composable
expect fun PermissionDialog(
    permission: String,
    permissionAction: (PermissionAction) -> Unit
)