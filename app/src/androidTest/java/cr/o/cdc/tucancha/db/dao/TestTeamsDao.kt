package cr.o.cdc.tucancha.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import cr.o.cdc.tucancha.db.Database
import cr.o.cdc.tucancha.mocks.MockPlayer
import cr.o.cdc.tucancha.mocks.MockSavedAt
import cr.o.cdc.tucancha.mocks.MockTeamEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TestTeamsDao {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val dao = Room.databaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        Database::class.java, "database"
    ).build().teamsDao()

    @Test
    fun insertAndGet() = runTest {
        val savedAt = 100L
        val mockPlayers = MockPlayer.getPlayers()
        val mockTeams = MockTeamEntity.getTeams(savedAt)
        dao.insert(mockTeams)
        dao.insert(MockSavedAt.getSavedAt(savedAt))
        val teams = dao.get().first()
        assertEquals(mockTeams.first().id, teams?.teams?.first()?.id)
        assertEquals(mockPlayers.first().id, teams?.teams?.first()?.players?.first()?.id)
        assertEquals(savedAt, teams?.savedTimeEntity?.timeStamp)
    }

    @Test
    fun insertManyItemsAndGetLast() = runTest {
        val mockPlayers = MockPlayer.getPlayers()
        for (i in 0..10) {
            val mockTeams = MockTeamEntity.getTeams(i.toLong(), i.toString())
            dao.insert(mockTeams)
            dao.insert(MockSavedAt.getSavedAt(i.toLong()))
        }

        val teams = dao.get().first()
        assertEquals("10", teams?.teams?.first()?.id)
        assertEquals(mockPlayers.first().id, teams?.teams?.first()?.players?.first()?.id)
        assertEquals(10L, teams?.savedTimeEntity?.timeStamp)
    }
}