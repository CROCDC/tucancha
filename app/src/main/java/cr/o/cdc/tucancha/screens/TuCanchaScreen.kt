package cr.o.cdc.tucancha.screens

import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import cr.o.cdc.tucancha.R
import cr.o.cdc.tucancha.mocks.MockPlayerView
import cr.o.cdc.tucancha.mocks.toState
import cr.o.cdc.tucancha.ui.theme.TuCanchaTheme
import cr.o.cdc.tucancha.vo.PlayerView
import kotlin.math.roundToInt

@Composable
fun TuCanchaScreen(
    players: State<List<PlayerView>>,
    onAddPlayerClick: () -> Unit = {},
    onDragFinish: (String, Float, Float) -> Unit,
    removePlayer: (String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.cancha),
                contentScale = ContentScale.FillBounds
            )
    ) {
        val (remove, addPlayer) = createRefs()
        players.value.forEach {
            PlayerCompose(
                player = it,
                onDragFinish = onDragFinish,
                onRemovePlayer = removePlayer
            )
        }
        AddPlayerButton(
            Modifier
                .padding(12.dp)
                .constrainAs(addPlayer) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            onAddPlayerClick.invoke()
        }
        RemovePlayerZone(
            Modifier
                .padding(12.dp)
                .constrainAs(remove) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
    }
}

@Composable
fun AddPlayerButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        shape = CircleShape,
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@Composable
fun RemovePlayerZone(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.delete),
        contentDescription = "delete zone"
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PlayerCompose(
    player: PlayerView,
    onDragFinish: (String, Float, Float) -> Unit,
    onRemovePlayer: (String) -> Unit
) {
    var isDragging by remember { mutableStateOf(false) }
    var offsetX by remember { mutableStateOf(player.x) }
    var offsetY by remember { mutableStateOf(player.y) }

    val display = LocalContext.current.resources.displayMetrics

    val screenHeight = display.heightPixels
    val screenWidth = display.widthPixels
    val percentage = 0.15
    val deleteZoneY = screenHeight - (screenHeight * percentage)
    val deleteZoneX = (screenWidth * percentage)

    Box(
        modifier = Modifier
            .testTag(player.id)
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInteropFilter { event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        isDragging = true
                        true
                    }

                    MotionEvent.ACTION_UP -> {
                        onDragFinish.invoke(
                            player.id,
                            offsetX,
                            offsetY
                        )
                        true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        if (isDragging) {
                            offsetX += event.x - 180
                            offsetY += event.y - 180

                        }
                        if (offsetX < deleteZoneX && offsetY > deleteZoneY) {
                            onRemovePlayer.invoke(player.id)
                        }
                        true
                    }

                    else -> false
                }
            }
    ) {
        AsyncImage(
            model = player.image,
            contentDescription = player.name,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AddPlayerButtonPreview() {
    TuCanchaTheme {
        AddPlayerButton {}
    }
}

@Preview(showBackground = true)
@Composable
fun TuCanchaScreenPreview() {
    TuCanchaScreen(
        players = listOf(MockPlayerView.getPlayerView1()).toState(),
        onDragFinish = { _, _, _ ->

        },
        removePlayer = {

        }
    )
}


