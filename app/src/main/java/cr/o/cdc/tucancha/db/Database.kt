package cr.o.cdc.tucancha.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cr.o.cdc.tucancha.datasources.model.Player
import cr.o.cdc.tucancha.db.dao.TeamsDao
import cr.o.cdc.tucancha.db.model.SavedAt
import cr.o.cdc.tucancha.db.model.TeamEntity

@Database(entities = [TeamEntity::class, SavedAt::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun teamsDao(): TeamsDao
}

class Converters {
    @TypeConverter
    fun playerToJson(cards: List<Player>): String = Gson().toJson(cards)

    @TypeConverter
    fun playerFromJson(json: String): List<Player> = Gson().fromJson(
        json,
        object : TypeToken<List<Player>>() {}.type
    )
}