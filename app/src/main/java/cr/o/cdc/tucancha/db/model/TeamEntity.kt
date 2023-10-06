package cr.o.cdc.tucancha.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import cr.o.cdc.tucancha.datasources.model.Player

@Entity
data class TeamEntity(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
    val players: List<Player>,
    val savedAt: Long
)