package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.datasources.model.Player

object MockPlayer {
    fun getPlayer1() = Player(
        "1",
        "name",
        "https://www.cariverplate.com.ar/images/logo-river.png?cache=a57"
    )

    fun getPlayers() = listOf(getPlayer1())
}

