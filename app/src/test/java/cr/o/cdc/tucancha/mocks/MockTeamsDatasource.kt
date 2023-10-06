package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.datasources.TeamsDatasource
import cr.o.cdc.tucancha.datasources.model.Team

class MockTeamsDatasource : TeamsDatasource {

    override suspend fun getTeams(): List<Team> = teams

    lateinit var teams: List<Team>
}