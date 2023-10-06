package cr.o.cdc.tucancha.db.model

import androidx.room.Embedded
import androidx.room.Relation

data class Teams(
    @Embedded val savedTimeEntity: SavedAt,
    @Relation(
        parentColumn = "timeStamp",
        entityColumn = "savedAt"
    )
    val teams: List<TeamEntity>
)