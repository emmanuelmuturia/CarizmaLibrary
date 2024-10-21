package emmanuelmuturia.sonux.ui.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import emmanuelmuturia.sonux.ui.colour.backgroundDark
import emmanuelmuturia.sonux.ui.colour.backgroundLight
import emmanuelmuturia.sonux.ui.colour.errorContainerDark
import emmanuelmuturia.sonux.ui.colour.errorDark
import emmanuelmuturia.sonux.ui.colour.errorLight
import emmanuelmuturia.sonux.ui.colour.inverseOnSurfaceDark
import emmanuelmuturia.sonux.ui.colour.inverseOnSurfaceLight
import emmanuelmuturia.sonux.ui.colour.inversePrimaryDark
import emmanuelmuturia.sonux.ui.colour.inversePrimaryLight
import emmanuelmuturia.sonux.ui.colour.inverseSurfaceDark
import emmanuelmuturia.sonux.ui.colour.inverseSurfaceLight
import emmanuelmuturia.sonux.ui.colour.onBackgroundDark
import emmanuelmuturia.sonux.ui.colour.onBackgroundLight
import emmanuelmuturia.sonux.ui.colour.onErrorContainerDark
import emmanuelmuturia.sonux.ui.colour.onErrorDark
import emmanuelmuturia.sonux.ui.colour.onErrorLight
import emmanuelmuturia.sonux.ui.colour.onPrimaryContainerDark
import emmanuelmuturia.sonux.ui.colour.onPrimaryDark
import emmanuelmuturia.sonux.ui.colour.onSecondaryContainerDark
import emmanuelmuturia.sonux.ui.colour.onSecondaryDark
import emmanuelmuturia.sonux.ui.colour.onSurfaceDark
import emmanuelmuturia.sonux.ui.colour.onSurfaceLight
import emmanuelmuturia.sonux.ui.colour.onSurfaceVariantDark
import emmanuelmuturia.sonux.ui.colour.onSurfaceVariantLight
import emmanuelmuturia.sonux.ui.colour.onTertiaryContainerDark
import emmanuelmuturia.sonux.ui.colour.onTertiaryContainerLight
import emmanuelmuturia.sonux.ui.colour.onTertiaryDark
import emmanuelmuturia.sonux.ui.colour.onTertiaryLight
import emmanuelmuturia.sonux.ui.colour.outlineDark
import emmanuelmuturia.sonux.ui.colour.outlineLight
import emmanuelmuturia.sonux.ui.colour.outlineVariantDark
import emmanuelmuturia.sonux.ui.colour.outlineVariantLight
import emmanuelmuturia.sonux.ui.colour.primaryContainerDark
import emmanuelmuturia.sonux.ui.colour.primaryDark
import emmanuelmuturia.sonux.ui.colour.primaryLight
import emmanuelmuturia.sonux.ui.colour.scrimDark
import emmanuelmuturia.sonux.ui.colour.scrimLight
import emmanuelmuturia.sonux.ui.colour.secondaryContainerDark
import emmanuelmuturia.sonux.ui.colour.secondaryContainerLight
import emmanuelmuturia.sonux.ui.colour.secondaryDark
import emmanuelmuturia.sonux.ui.colour.surfaceBrightDark
import emmanuelmuturia.sonux.ui.colour.surfaceBrightLight
import emmanuelmuturia.sonux.ui.colour.surfaceContainerDark
import emmanuelmuturia.sonux.ui.colour.surfaceContainerHighDark
import emmanuelmuturia.sonux.ui.colour.surfaceContainerHighLight
import emmanuelmuturia.sonux.ui.colour.surfaceContainerHighestDark
import emmanuelmuturia.sonux.ui.colour.surfaceContainerHighestLight
import emmanuelmuturia.sonux.ui.colour.surfaceContainerLight
import emmanuelmuturia.sonux.ui.colour.surfaceContainerLowDark
import emmanuelmuturia.sonux.ui.colour.surfaceContainerLowLight
import emmanuelmuturia.sonux.ui.colour.surfaceContainerLowestDark
import emmanuelmuturia.sonux.ui.colour.surfaceContainerLowestLight
import emmanuelmuturia.sonux.ui.colour.surfaceDark
import emmanuelmuturia.sonux.ui.colour.surfaceDimDark
import emmanuelmuturia.sonux.ui.colour.surfaceDimLight
import emmanuelmuturia.sonux.ui.colour.surfaceLight
import emmanuelmuturia.sonux.ui.colour.surfaceVariantDark
import emmanuelmuturia.sonux.ui.colour.surfaceVariantLight
import emmanuelmuturia.sonux.ui.colour.tertiaryContainerDark
import emmanuelmuturia.sonux.ui.colour.tertiaryContainerLight
import emmanuelmuturia.sonux.ui.colour.tertiaryDark
import emmanuelmuturia.sonux.ui.colour.onPrimaryLight
import emmanuelmuturia.sonux.ui.colour.primaryContainerLight
import emmanuelmuturia.sonux.ui.colour.onPrimaryContainerLight
import emmanuelmuturia.sonux.ui.colour.secondaryLight
import emmanuelmuturia.sonux.ui.colour.onSecondaryLight
import emmanuelmuturia.sonux.ui.colour.onSecondaryContainerLight
import emmanuelmuturia.sonux.ui.colour.tertiaryLight
import emmanuelmuturia.sonux.ui.colour.errorContainerLight
import emmanuelmuturia.sonux.ui.colour.onErrorContainerLight
import emmanuelmuturia.sonux.ui.typography.sonuxTypography

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

@Composable
fun SonuxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = sonuxTypography,
        content = content
    )
}