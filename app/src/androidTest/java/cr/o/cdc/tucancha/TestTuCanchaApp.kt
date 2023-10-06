package cr.o.cdc.tucancha

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestTuCanchaApp {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            TuCanchaApp(navController = navController)
        }
    }

    @Test
    fun navHost_verifySelectTeamDestination() {
        composeTestRule.onNode(hasClickAction()).performClick()
        assertEquals(
            MainActivity.selectTeamRoute,
            navController.currentDestination?.route
        )
    }
}
