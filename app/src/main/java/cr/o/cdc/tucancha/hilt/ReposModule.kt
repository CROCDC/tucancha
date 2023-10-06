package cr.o.cdc.tucancha.hilt

import cr.o.cdc.tucancha.repos.TeamsRepository
import cr.o.cdc.tucancha.repos.TeamsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReposModule {

    @Binds
    abstract fun bindTeamRepository(repository: TeamsRepositoryImp): TeamsRepository

}