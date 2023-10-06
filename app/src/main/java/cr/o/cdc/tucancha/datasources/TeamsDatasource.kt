package cr.o.cdc.tucancha.datasources

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cr.o.cdc.tucancha.datasources.model.Team

interface TeamsDatasource {

    suspend fun getTeams(): List<Team>
}

class TeamsDatasourceImp(private val datasource: FirebaseDatasource) : TeamsDatasource {

    override suspend fun getTeams(): List<Team> = Gson().fromJson(
        Gson().toJson(datasource.getTeams()),
        object : TypeToken<List<Team>>() {}.type
    )
}