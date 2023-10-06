package cr.o.cdc.tucancha.hilt

import com.google.firebase.database.FirebaseDatabase
import cr.o.cdc.tucancha.datasources.FirebaseDatasource
import cr.o.cdc.tucancha.datasources.TeamsDatasource
import cr.o.cdc.tucancha.datasources.TeamsDatasourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataSourceModule {
    @Provides
    fun providesCardsDataSource(datasource: FirebaseDatasource): TeamsDatasource =
        TeamsDatasourceImp(datasource)
}