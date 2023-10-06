package cr.o.cdc.tucancha.screens

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performMouseInput
import cr.o.cdc.tucancha.mocks.MockPlayerView
import cr.o.cdc.tucancha.mocks.toState
import cr.o.cdc.tucancha.ui.theme.TuCanchaTheme
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestTuCanchaScreen {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var addedPlayerCalled: Boolean = false

    private var removePlayerCalled: Boolean = false

    private var x: Float = 0f
    private var y: Float = 0f
    private lateinit var id: String

    @Before
    fun setupRule() {
        composeTestRule.setContent {
            TuCanchaTheme {
                TuCanchaScreen(
                    MockPlayerView.getPlayers().toState(),
                    onAddPlayerClick = {
                        addedPlayerCalled = true
                    },
                    onDragFinish = { s: String, x: Float, y: Float ->
                        this.x = x
                        this.y = y
                        id = s
                    },
                    removePlayer = {
                        removePlayerCalled = true
                    }
                )
            }
        }
    }

    @Test
    fun showPlayers() {
        val player = MockPlayerView.getPlayers()[0]
        composeTestRule.onNode(hasTestTag(player.id)).assertExists()
    }

    @Test
    fun moveAndDelete() {
        val player = MockPlayerView.getPlayers()[0]
        val composePlayer = composeTestRule.onNode(hasTestTag(player.id))
        composePlayer.move(600f, 600f)
        //reduce the move 180f offset
        assertEquals(x, 420f)
        assertEquals(y, 420f)
        //move delete zone
        Thread.sleep(500)
        composePlayer.move(0f, 1000000f)
        assert(removePlayerCalled)
    }

    @Test
    fun onAddPlayerClick() {
        composeTestRule.onNode(hasClickAction()).performClick()
        assert(addedPlayerCalled)
    }

    @OptIn(ExperimentalTestApi::class)
    fun SemanticsNodeInteraction.move(x: Float, y: Float) {
        this.performMouseInput {
            press()
            moveTo(Offset(x, y))
            release()
        }
    }
}