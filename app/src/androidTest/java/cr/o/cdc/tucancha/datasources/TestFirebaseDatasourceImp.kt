package cr.o.cdc.tucancha.datasources

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.time.Duration

class TestFirebaseDatasourceImp {
    private val firebaseDatabase = FirebaseDatasourceImp()


    @Test
    fun getTeams() = runTest(timeout = Duration.INFINITE) {
        val river = firebaseDatabase.getTeams()[0]
        assertEquals(
            "River",
            river["name"]
        )
        assertEquals(
            "Franco Armani",
            (river["players"] as ArrayList<HashMap<String,Any>>)[0]["name"]
        )
    }
}