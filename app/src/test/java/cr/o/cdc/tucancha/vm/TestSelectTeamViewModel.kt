package cr.o.cdc.tucancha.vm

import cr.o.cdc.tucancha.mocks.MockTeamEntity
import cr.o.cdc.tucancha.mocks.MockTeams
import cr.o.cdc.tucancha.mocks.MockTeamsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TestSelectTeamViewModel {

    private val repository = MockTeamsRepository()

    private val viewModel = SelectTeamViewModel(repository)

    @Test
    fun getTeams() = runTest {
        repository.teams = MockTeams.getTeams()
        assertEquals(
            MockTeamEntity.ID_1,
            viewModel.teams.first().data?.teams?.get(0)?.id
        )
    }
}