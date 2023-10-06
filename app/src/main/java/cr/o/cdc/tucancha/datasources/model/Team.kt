package cr.o.cdc.tucancha.datasources.model


data class Team(
    val id: String,
    val name: String,
    val image: String,
    val players: List<Player>
)