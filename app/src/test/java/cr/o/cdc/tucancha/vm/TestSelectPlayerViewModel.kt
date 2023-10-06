package cr.o.cdc.tucancha.vm

import cr.o.cdc.tucancha.mocks.MockTeamEntity
import cr.o.cdc.tucancha.mocks.MockTeamsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestSelectPlayerViewModel {

    private val repository = MockTeamsRepository()

    private val viewModel: SelectPlayerViewModel = SelectPlayerViewModel(repository)

    @Test
    fun getTeamEntity() = runTest {
        val team = MockTeamEntity.getTeam1()
        repository.teamEntity = team
        assertEquals(
            MockTeamEntity.ID_1,
            viewModel.getTeam(MockTeamEntity.ID_1).first()?.id
        )
    }
}