package ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import emmanuelmuturia.sonux.screens.ConfirmationScreen
import kotlin.test.Test

class ConfirmationScreenUITest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testConfirmationScreenUIElements() = runComposeUiTest {

        setContent {
            ConfirmationScreen()
        }

        // Write the Assertions from here...

    }

}