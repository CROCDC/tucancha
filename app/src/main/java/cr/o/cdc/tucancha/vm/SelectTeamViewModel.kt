package cr.o.cdc.tucancha.vm

import androidx.lifecycle.ViewModel
import cr.o.cdc.tucancha.db.model.Teams
import cr.o.cdc.tucancha.networking.Resource
import cr.o.cdc.tucancha.repos.TeamsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SelectTeamViewModel @Inject constructor(
    repository: TeamsRepository
) : ViewModel() {
    val teams: Flow<Resource<Teams?>> = repository.getTeams()
}