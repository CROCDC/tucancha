package cr.o.cdc.tucancha.hilt

import cr.o.cdc.tucancha.datasources.FirebaseDatasource
import cr.o.cdc.tucancha.datasources.FirebaseDatasourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    fun provideFirebaseDatabase(): FirebaseDatasource = FirebaseDatasourceImp()

}