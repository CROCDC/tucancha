package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.db.model.TeamEntity
import cr.o.cdc.tucancha.db.model.Teams
import cr.o.cdc.tucancha.networking.Resource
import cr.o.cdc.tucancha.repos.TeamsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class MockTeamsRepository : TeamsRepository {
    override fun getTeams(): Flow<Resource<Teams?>> = flow {
        emit(Resource.Success(teams))
    }

    override fun getTeam(id: String): Flow<TeamEntity?> = flow {
        emit(teamEntity.takeIf { it.id == id })
    }

    lateinit var teams: Teams
    lateinit var teamEntity: TeamEntity
}