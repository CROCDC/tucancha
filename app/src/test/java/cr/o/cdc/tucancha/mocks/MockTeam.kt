package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.datasources.model.Team

object MockTeam {

    fun getTeam(id: String) = Team(
        id,
        "name",
        "image",
        MockPlayer.getPlayers()
    )

    fun getTeams(id1: String) = listOf(getTeam(id1))
}