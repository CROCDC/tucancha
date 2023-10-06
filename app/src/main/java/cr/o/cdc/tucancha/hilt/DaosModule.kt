package cr.o.cdc.tucancha.hilt

import cr.o.cdc.tucancha.db.Database
import cr.o.cdc.tucancha.db.dao.TeamsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DaosModule {

    @Provides
    fun providesTeamsDao(database: Database): TeamsDao = database.teamsDao()
}