package cr.o.cdc.tucancha.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedAt(
    @PrimaryKey val timeStamp: Long,
)