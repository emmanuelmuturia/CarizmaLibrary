package ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import emmanuelmuturia.sonux.screens.HomeScreen
import kotlin.test.Test

class HomeScreenUITest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testHomeScreenUIElements() = runComposeUiTest {

        setContent {
            HomeScreen()
        }

        // Write the assertions from here...

    }

}