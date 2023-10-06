package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.datasources.FirebaseDatasource

class MockFirebaseDatasource : FirebaseDatasource {
    override suspend fun getTeams(): ArrayList<HashMap<String, Any>> = arrayListOf(
        hashMapOf<String, Any>(
            "name" to "name 1",
            "image" to "image 1",
            "players" to arrayListOf<HashMap<String, Any>>(
                hashMapOf(
                    "name" to "nameP 1",
                    "image" to "imageP 1"
                ),
                hashMapOf(
                    "name" to "name 2",
                    "image" to "image 2"
                )
            )
        )
    )
}