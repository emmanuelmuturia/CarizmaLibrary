package emmanuelmuturia.sonux.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import emmanuelmuturia.sonux.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        floatingActionButton = {
            HomeScreenButton()
        }
    ) { paddingValues ->
        HomeScreenContent(modifier = Modifier.padding(paddingValues = paddingValues))
    }

}

@Composable
internal fun HomeScreenContent(modifier: Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(all = 7.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item(key = "HomeScreenAnimation") {
            HomeScreenAnimation()
        }

        item(key = "HomeScreenText") {
            HomeScreenText()
        }
    }
}

@Composable
internal fun HomeScreenAnimation() {

}

@Composable
internal fun HomeScreenText() {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle()) {
            append(text = "Supported File Formats: ")
        }
        append(text = "MP3, WAV, FLAC, and OGG")
    })
}

@Composable
internal fun HomeScreenButton() {
    FloatingActionButton(onClick = {
        // Select the audio file...
    }, containerColor = MaterialTheme.colorScheme.primary) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Select Audio File Button",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}