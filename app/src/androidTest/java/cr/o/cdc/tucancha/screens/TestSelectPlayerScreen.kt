package cr.o.cdc.tucancha.screens

import androidx.compose.ui.test.junit4.createComposeRule
import cr.o.cdc.tucancha.mocks.MockTeamEntity
import cr.o.cdc.tucancha.mocks.toState
import cr.o.cdc.tucancha.ui.theme.TuCanchaTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestSelectPlayerScreen {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setupRule() {
        composeTestRule.setContent {
            TuCanchaTheme {
                SelectPlayerScreen(
                    MockTeamEntity.getTeam1().toState(),
                    {}
                )
            }
        }
    }

    @Test
    fun showPlayers() {
        Thread.sleep(10000)
    }
}