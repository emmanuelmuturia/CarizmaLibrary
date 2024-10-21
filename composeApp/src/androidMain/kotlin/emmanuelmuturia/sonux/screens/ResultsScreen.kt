package emmanuelmuturia.sonux.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Results",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { paddingValues ->
        ResultsScreenContent(modifier = Modifier.padding(paddingValues = paddingValues))
    }

}

@Composable
internal fun ResultsScreenContent(modifier: Modifier) {

}

@Composable
internal fun ResultsScreenAnimation() {

}

@Composable
internal fun ResultsConfirmationText() {

}

@Composable
internal fun DownloadButton() {

}

@Composable
internal fun HomeScreenNavigationButton() {

}