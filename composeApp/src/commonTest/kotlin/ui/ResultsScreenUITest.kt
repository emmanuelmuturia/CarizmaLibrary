package ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import emmanuelmuturia.sonux.screens.ResultsScreen
import kotlin.test.Test

class ResultsScreenUITest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testResultsScreenUIElements() = runComposeUiTest {

        setContent {
            ResultsScreen()
        }

        // Write the Assertions from here...

    }

}