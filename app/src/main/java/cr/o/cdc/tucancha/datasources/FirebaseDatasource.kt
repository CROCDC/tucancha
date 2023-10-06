package cr.o.cdc.tucancha.datasources

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

interface FirebaseDatasource {
    suspend fun getTeams(): ArrayList<HashMap<String, Any>>
}

class FirebaseDatasourceImp : FirebaseDatasource {

    override suspend fun getTeams(): ArrayList<HashMap<String, Any>> =
        Firebase.database.getReference("teams").get().await().value
                as ArrayList<HashMap<String, Any>>

}