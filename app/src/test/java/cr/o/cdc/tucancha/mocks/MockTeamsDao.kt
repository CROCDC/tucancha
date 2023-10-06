package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.db.dao.TeamsDao
import cr.o.cdc.tucancha.db.model.SavedAt
import cr.o.cdc.tucancha.db.model.TeamEntity
import cr.o.cdc.tucancha.db.model.Teams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockTeamsDao : TeamsDao {
    override fun get(): Flow<Teams?> = flow {
        emit(teams)
    }

    override fun get(id: String): Flow<TeamEntity?> = flow {

    }

    override fun insert(teams: List<TeamEntity>) {
        this.teams = this.teams.copy(
            teams = teams
        )
    }

    override fun insert(savedAt: SavedAt) {
        this.teams = this.teams.copy(
            savedTimeEntity = savedAt
        )
    }

    override fun deleteSavedAt() {}

    override fun deleteTeams() {}

    lateinit var teams: Teams
}