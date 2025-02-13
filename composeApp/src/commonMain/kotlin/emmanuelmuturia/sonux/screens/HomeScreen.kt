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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import emmanuelmuturia.sonux.viewmodel.SonuxViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sonux.composeapp.generated.resources.Res
import sonux.composeapp.generated.resources.dark_home_screen
import sonux.composeapp.generated.resources.light_home_screen

class HomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val sonuxViewModel: SonuxViewModel = koinViewModel()
        Scaffold(
            modifier = Modifier.fillMaxSize().background(
                color = MaterialTheme.colorScheme.background
            ),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            modifier = Modifier.semantics {
                                contentDescription = "Sonux Top App Bar"
                            },
                            text = "Sonux",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            floatingActionButton = {
                HomeScreenButton(sonuxViewModel = sonuxViewModel)
            }
        ) { paddingValues ->
            HomeScreenContent(modifier = Modifier.padding(paddingValues = paddingValues))
        }
    }
}

@Composable
internal fun HomeScreenContent(modifier: Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(all = 14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item(key = "HomeScreenImage") {
            HomeScreenImage()
        }

        item(key = "HomeScreenText") {
            HomeScreenText()
        }
    }
}

@Composable
internal fun HomeScreenImage() {
    Image(
        painter = painterResource(
            resource = if (isSystemInDarkTheme()) {
                Res.drawable.dark_home_screen
            } else {
                Res.drawable.light_home_screen
            }
        ),
        contentDescription = "Home Screen Image"
    )

    Spacer(modifier = Modifier.height(height = 21.dp))
}

@Composable
internal fun HomeScreenText() {
    Text(
        modifier = Modifier.semantics {
            contentDescription = "Home Screen Text"
        },
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                append(text = "Supported File Formats: ")
            }
            append(text = "MP3, WAV, FLAC, and OGG")
        }
    )
}

@Composable
internal expect fun HomeScreenButton(sonuxViewModel: SonuxViewModel)