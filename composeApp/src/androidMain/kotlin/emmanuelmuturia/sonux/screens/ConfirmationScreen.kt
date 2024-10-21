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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import emmanuelmuturia.sonux.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { paddingValues ->
        ConfirmationScreenContent(modifier = Modifier.padding(paddingValues = paddingValues))
    }

}

@Composable
internal fun ConfirmationScreenContent(modifier: Modifier) {

}

@Composable
internal fun AudioFileDetails() {

}

@Composable
internal fun ConvertButton() {

}