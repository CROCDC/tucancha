package cr.o.cdc.tucancha.mocks

import cr.o.cdc.tucancha.vo.PlayerView

object MockPlayerView {

    fun getPlayers() = listOf(getPlayerView1())

    fun getPlayerView1() = PlayerView(
        "1",
        "name",
        "https://www.cariverplate.com.ar/images/logo-river.png?cache=a57",
        0f,
        0f
    )
}