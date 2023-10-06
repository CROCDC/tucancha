package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.db.model.TeamEntity

object MockTeamEntity {
    const val ID_1 = "club1"
    const val ID_2 = "club2"

    const val IMAGE_1 =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Escudo_del_C_A_River_Plate.svg/1200px-Escudo_del_C_A_River_Plate.svg.png"

    fun getTeam1(savedAt: Long = 0L, id: String = ID_1) = TeamEntity(
        id,
        "name".repeat(10),
        IMAGE_1,
        MockPlayer.getPlayers(),
        savedAt
    )

    fun getTeam2(savedAt: Long = 0L, id: String = ID_2) = TeamEntity(
        id,
        "name 2",
        IMAGE_1,
        MockPlayer.getPlayers(),
        savedAt
    )

    fun getTeams(savedAt: Long = 0L, id1: String = ID_1) = listOf(
        getTeam1(savedAt, id1),
        getTeam2(savedAt, id1)
    )
}

