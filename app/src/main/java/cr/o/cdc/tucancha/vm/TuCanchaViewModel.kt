package cr.o.cdc.tucancha.vm

import androidx.lifecycle.ViewModel
import cr.o.cdc.tucancha.datasources.model.Player
import cr.o.cdc.tucancha.vo.PlayerView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TuCanchaViewModel : ViewModel() {

    private val _addedPlayers: MutableStateFlow<List<PlayerView>> = MutableStateFlow(emptyList())

    val addedPlayers: Flow<List<PlayerView>>
        get() = _addedPlayers

    fun addPlayer(player: Player) {
        _addedPlayers.value = _addedPlayers.value.plus(
            PlayerView(player.id, player.name, player.image, 0F, 0F)
        )
    }

    fun movePlayer(id: String, x: Float, y: Float) {
        val players = _addedPlayers.value
        _addedPlayers.value = players.map {
            if (id == it.id) {
                it.copy(
                    x = x,
                    y = y
                )
            } else {
                it
            }
        }
    }

    fun removePlayer(id: String) {
        val players = _addedPlayers.value
        players.find { it.id == id }?.let {
            _addedPlayers.value = players.minus(it)
        }
    }

}