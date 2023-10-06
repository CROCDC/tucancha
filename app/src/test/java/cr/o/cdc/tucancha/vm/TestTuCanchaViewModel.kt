package cr.o.cdc.tucancha.vm

import cr.o.cdc.tucancha.mocks.MockPlayer
import cr.o.cdc.tucancha.mocks.MockPlayerView
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TestTuCanchaViewModel {

    private val vm = TuCanchaViewModel()

    @Test
    fun addPlayer() = runTest {
        val player = MockPlayer.getPlayer1()
        vm.addPlayer(player)
        assertEquals(
            MockPlayerView.getPlayerView1(),
            vm.addedPlayers.first()[0]
        )
    }

    @Test
    fun movePlayer() = runTest {
        val player = MockPlayer.getPlayer1()
        vm.addPlayer(player)
        vm.movePlayer(
            player.id,
            5F,
            10F
        )
        assertEquals(
            MockPlayerView.getPlayerView1().copy(
                x = 5F,
                y = 10F
            ),
            vm.addedPlayers.first()[0]
        )
    }

    @Test
    fun removePlayer() = runTest {
        val player = MockPlayer.getPlayer1()
        vm.addPlayer(player)
        vm.removePlayer(player.id)
        assertEquals(
            null,
            vm.addedPlayers.first().getOrNull(0)
        )
    }
}