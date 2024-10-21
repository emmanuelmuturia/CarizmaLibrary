package emmanuelmuturia.sonux.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import emmanuelmuturia.sonux.R

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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                navigationIcon = {
                    IconButton(onClick = {
                        // Navigate back...
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
        ResultsScreenContent(modifier = Modifier.padding(paddingValues = paddingValues))
    }

}

@Composable
internal fun ResultsScreenContent(modifier: Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(all = 7.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item(
            key = "ResultsConfirmationText"
        ) {
            ResultsConfirmationText()
        }

        item(
            key = "DownloadButton"
        ) {
            DownloadButton()
        }

        item(
            key = "NavigateToScreenButton"
        ) {
            NavigateToScreenButton()
        }
    }
}

@Composable
internal fun ResultsConfirmationText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.green_checkmark),
            contentDescription = "Success Checkmark"
        )

        Text(
            text = "Your audio file was successfully converted to 8D...",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
internal fun DownloadButton() {
    Button(onClick = {
        // Download the converted the audio file...
    }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
        Text(text = "Download", style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
internal fun NavigateToScreenButton() {
    Button(onClick = {
        // Navigate to the Home Screen...
    }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
        Text(text = "Convert another audio file...", style = MaterialTheme.typography.labelLarge)
    }
}