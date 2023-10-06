package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.db.model.Teams

object MockTeams {

    fun getTeams(savedAt: Long = 0L, id1: String = MockTeamEntity.ID_1) = Teams(
        MockSavedAt.getSavedAt(savedAt),
        MockTeamEntity.getTeams(savedAt, id1)
    )
}