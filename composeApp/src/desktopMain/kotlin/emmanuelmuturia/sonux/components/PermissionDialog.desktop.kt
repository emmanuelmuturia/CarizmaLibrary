package emmanuelmuturia.sonux.components

import androidx.compose.runtime.Composable
import emmanuelmuturia.sonux.permissions.PermissionAction

@Composable
actual fun PermissionDialog(
    permission: String,
    permissionAction: (PermissionAction) -> Unit
) {
}