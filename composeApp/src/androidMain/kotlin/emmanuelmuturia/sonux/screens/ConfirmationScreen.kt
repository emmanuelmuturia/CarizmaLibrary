package emmanuelmuturia.sonux.screens

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

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
        ConfirmationScreenContent(modifier = Modifier.padding(paddingValues = paddingValues))
    }

}

@Composable
internal fun ConfirmationScreenContent(modifier: Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(all = 7.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item(key = "AudioFileDetails") {
            AudioFileDetails()
        }

        item(key = "ConvertButton") {
            ConvertButton()
        }
    }
}

@Composable
internal fun AudioFileDetails() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle()) {
                append(text = "Audio Title: ")
            }
            append(text = "Sample Audio Title")
        })

        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle()) {
                append(text = "Audio Type: ")
            }
            append(text = "Sample Audio Type")
        })

        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle()) {
                append(text = "Audio Size: ")
            }
            append(text = "Sample Audio Size")
        })
    }
}

@Composable
internal fun ConvertButton() {
    Button(onClick = {
        // Convert the audio file...
    }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
        Text(text = "Convert", style = MaterialTheme.typography.labelLarge)
    }
}