package cr.o.cdc.tucancha.repos

import cr.o.cdc.tucancha.datasources.TeamsDatasource
import cr.o.cdc.tucancha.db.dao.TeamsDao
import cr.o.cdc.tucancha.db.model.SavedAt
import cr.o.cdc.tucancha.db.model.TeamEntity
import cr.o.cdc.tucancha.db.model.Teams
import cr.o.cdc.tucancha.networking.Resource
import cr.o.cdc.tucancha.networking.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TeamsRepository {

    fun getTeams(): Flow<Resource<Teams?>>

    fun getTeam(id: String): Flow<TeamEntity?>

}

class TeamsRepositoryImp @Inject constructor(
    private val dao: TeamsDao,
    private val datasource: TeamsDatasource
) : TeamsRepository {

    override fun getTeams() = networkBoundResource(
        {
            dao.get()
        },
        {
            datasource.getTeams()
        },
        { teams ->
            dao.deleteTeams()
            dao.deleteSavedAt()
            val timestamp = System.currentTimeMillis()
            dao.insert(
                teams.map {
                    TeamEntity(
                        it.id,
                        it.name,
                        it.image,
                        it.players,
                        timestamp
                    )
                }
            )
            dao.insert(SavedAt(timestamp))
        },
        shouldFetch = {
            it?.let {
                System.currentTimeMillis() - it.savedTimeEntity.timeStamp >= 7 * 24 * 60 * 60 * 1000
            } ?: true
        }
    )

    override fun getTeam(id: String): Flow<TeamEntity?> = dao.get(id)
}