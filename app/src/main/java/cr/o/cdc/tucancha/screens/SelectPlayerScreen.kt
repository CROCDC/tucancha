package cr.o.cdc.tucancha.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import cr.o.cdc.tucancha.R
import cr.o.cdc.tucancha.datasources.model.Player
import cr.o.cdc.tucancha.db.model.TeamEntity
import cr.o.cdc.tucancha.mocks.MockPlayer
import cr.o.cdc.tucancha.mocks.MockTeamEntity
import cr.o.cdc.tucancha.mocks.toState
import cr.o.cdc.tucancha.ui.theme.TuCanchaTheme
import cr.o.cdc.tucancha.vm.SelectPlayerViewModel

@Composable
fun SelectPlayerRoute(onClickPlayerRow: (Player) -> Unit, teamId: String) {
    val vm: SelectPlayerViewModel = hiltViewModel()
    SelectPlayerScreen(
        team = vm.getTeam(teamId).collectAsState(initial = null),
        onClickPlayerRow
    )
}

@Composable
fun SelectPlayerScreen(team: State<TeamEntity?>, onClickPlayerRow: (Player) -> Unit) {
    TuCanchaTheme {
        LazyColumn {
            items(team.value?.players ?: listOf()) {
                PlayerItem(player = it, onClickPlayerRow)
            }
        }
    }
}

@Composable
fun PlayerItem(player: Player, onClickPlayerRow: (Player) -> Unit) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable {
            onClickPlayerRow.invoke(player)
        }) {
        val (image, title) = createRefs()
        AsyncImage(
            model = player.image,
            contentDescription = "imagen del club",
            placeholder = painterResource(id = R.drawable.baseline_image_180),
            modifier = Modifier
                .width(180.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, 10.dp)
                }
        )
        Text(
            color = colorResource(id = R.color.black),
            text = player.name,
            fontSize = 24.sp,
            modifier = Modifier
                .constrainAs(title) {
                    bottom.linkTo(image.bottom)
                    top.linkTo(image.top)
                    start.linkTo(image.end, 30.dp)
                    end.linkTo(parent.end, 30.dp)
                    width = Dimension.fillToConstraints
                }
        )
    }
}

@Preview
@Composable
fun PlayerItemPreview() {
    TuCanchaTheme {
        PlayerItem(player = MockPlayer.getPlayer1()) {
        }
    }
}

@Preview
@Composable
fun SelectPlayerScreenPreview() {
    SelectPlayerScreen(team = MockTeamEntity.getTeam1().toState()) {}
}