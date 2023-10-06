package cr.o.cdc.tucancha.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cr.o.cdc.tucancha.mocks.MockTeam
import cr.o.cdc.tucancha.mocks.MockTeams
import cr.o.cdc.tucancha.mocks.MockTeamsDao
import cr.o.cdc.tucancha.mocks.MockTeamsDatasource
import cr.o.cdc.tucancha.networking.Resource
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TestTeamsRepository {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val dao = MockTeamsDao()
    private val datasource = MockTeamsDatasource()

    private val repository = TeamsRepositoryImp(
        dao,
        datasource
    )


    @Test
    fun getTeamsShouldFetchTrueDaoOutDated() = runTest {
        dao.teams = MockTeams.getTeams(10, DAO)
        datasource.teams = MockTeam.getTeams(DATASOURCE)
        val flow = repository.getTeams()
        launch {
            flow.collect {
                if (it !is Resource.Loading) {
                    Assert.assertEquals(
                        DATASOURCE,
                        it.data?.teams?.get(0)?.id
                    )
                    this.cancel()
                }
            }
        }
    }

    @Test
    fun getTeamsShouldFetchFalseDaoUpdated() = runTest {
        dao.teams = MockTeams.getTeams(System.currentTimeMillis(), DAO)
        datasource.teams = MockTeam.getTeams(DATASOURCE)
        val flow = repository.getTeams()
        launch {
            flow.collect {
                if (it !is Resource.Loading) {
                    Assert.assertEquals(
                        DAO,
                        it.data?.teams?.get(0)?.id
                    )
                    this.cancel()
                }
            }
        }
    }

    companion object {
        const val DAO = "DAO"
        const val DATASOURCE = "DATASOURCE"
    }
}