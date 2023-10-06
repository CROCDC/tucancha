package cr.o.cdc.tucancha.datasources

import cr.o.cdc.tucancha.mocks.MockFirebaseDatasource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TestTeamsDatasourceImp {

    private val datasource = TeamsDatasourceImp(MockFirebaseDatasource())

    @Test
    fun getTeams() = runTest {
        val team = datasource.getTeams()[0]
        val player1 = team.players[0]
        assertEquals("name 1", team.name)
        assertEquals("image 1", team.image)
        assertEquals("nameP 1", player1.name)
        assertEquals("imageP 1", player1.image)
    }
}