package cr.o.cdc.tucancha.vm

import androidx.lifecycle.ViewModel
import cr.o.cdc.tucancha.db.model.TeamEntity
import cr.o.cdc.tucancha.repos.TeamsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SelectPlayerViewModel @Inject constructor(private val repository: TeamsRepository) :
    ViewModel() {

    fun getTeam(id: String): Flow<TeamEntity?> = repository.getTeam(id)

}