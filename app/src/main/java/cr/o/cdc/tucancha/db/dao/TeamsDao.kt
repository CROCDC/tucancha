package cr.o.cdc.tucancha.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cr.o.cdc.tucancha.db.model.SavedAt
import cr.o.cdc.tucancha.db.model.TeamEntity
import cr.o.cdc.tucancha.db.model.Teams
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamsDao {

    @Query("SELECT * FROM savedat ORDER BY timestamp DESC LIMIT 1")
    fun get(): Flow<Teams?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teams: List<TeamEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(savedAt: SavedAt)

    @Query("SELECT * FROM TeamEntity WHERE id = :id")
    fun get(id: String): Flow<TeamEntity?>

    @Query("DELETE FROM SavedAt")
    fun deleteSavedAt()

    @Query("DELETE FROM TeamEntity")
    fun deleteTeams()


}