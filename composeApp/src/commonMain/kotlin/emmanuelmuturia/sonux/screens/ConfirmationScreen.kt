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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import emmanuelmuturia.sonux.viewmodel.SonuxViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sonux.composeapp.generated.resources.Res
import sonux.composeapp.generated.resources.dark_confirmation_screen
import sonux.composeapp.generated.resources.light_confirmation_screen

data class ConfirmationScreen(val audioFileUri: String) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val sonuxViewModel: SonuxViewModel = koinViewModel()
        val navigator = LocalNavigator.current
        Scaffold(
            modifier = Modifier.fillMaxSize().background(
                color = MaterialTheme.colorScheme.background
            ),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Confirmation",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator?.pop()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "Navigate Back Icon",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            ConfirmationScreenContent(
                modifier = Modifier.padding(paddingValues = paddingValues),
                uri = this.audioFileUri
            )
        }
    }
}

@Composable
internal fun ConfirmationScreenContent(
    modifier: Modifier,
    uri: String
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(all = 7.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ConfirmationScreenImage()
        }

        item(key = "AudioFileDetails") {
            AudioFileDetails(uri = uri)
        }

        item(key = "ConvertButton") {
            ConvertButton(audioFileUri = uri)
        }
    }
}

@Composable
fun ConfirmationScreenImage() {
    Image(
        painter = painterResource(
            resource = if (isSystemInDarkTheme()) {
                Res.drawable.dark_confirmation_screen
            } else {
                Res.drawable.light_confirmation_screen
            }
        ),
        contentDescription = "Confirmation Screen Image"
    )
}

@Composable
internal expect fun AudioFileDetails(uri: String)

@Composable
internal fun ConvertButton(audioFileUri: String) {
    val navigator = LocalNavigator.current
    Button(onClick = {
        navigator?.push(item = ResultsScreen(audioFileUri = audioFileUri))
    }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
        Text(text = "Confirm", style = MaterialTheme.typography.labelLarge)
    }
}