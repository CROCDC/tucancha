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
import cr.o.cdc.tucancha.composes.ErrorCompose
import cr.o.cdc.tucancha.composes.LoadingCompose
import cr.o.cdc.tucancha.db.model.TeamEntity
import cr.o.cdc.tucancha.db.model.Teams
import cr.o.cdc.tucancha.mocks.MockTeamEntity
import cr.o.cdc.tucancha.mocks.MockTeams
import cr.o.cdc.tucancha.mocks.errorState
import cr.o.cdc.tucancha.mocks.successState
import cr.o.cdc.tucancha.networking.Resource
import cr.o.cdc.tucancha.ui.theme.TuCanchaTheme
import cr.o.cdc.tucancha.vm.SelectTeamViewModel

@Composable
fun SelectTeamRoute(onClickTeamRow: (String) -> Unit) {
    val vm: SelectTeamViewModel = hiltViewModel()
    SelectTeamScreen(
        teams = vm.teams.collectAsState(initial = null),
        onClickTeamRow
    )
}

@Composable
fun SelectTeamScreen(teams: State<Resource<Teams?>?>, onClickTeamRow: (String) -> Unit) {
    TuCanchaTheme {
        when (teams.value) {
            is Resource.Error -> ErrorCompose()
            is Resource.Loading -> LoadingCompose()
            is Resource.Success -> TeamsItemsList(
                teams = teams.value?.data?.teams,
                onClickTeamRow = onClickTeamRow
            )

            null -> {}
        }
    }


}

@Composable
fun TeamsItemsList(teams: List<TeamEntity>?, onClickTeamRow: (String) -> Unit) {
    LazyColumn {
        items(teams ?: emptyList()) {
            TeamItem(team = it, onClickTeamRow)
        }
    }
}


@Composable
fun TeamItem(team: TeamEntity, onClickTeamRow: (String) -> Unit) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable {
            onClickTeamRow.invoke(team.id)
        }) {
        val (image, title) = createRefs()
        AsyncImage(
            model = team.image,
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
            text = team.name,
            fontSize = 30.sp,
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

@Preview(showBackground = true)
@Composable
fun TeamItemPreview() {
    TuCanchaTheme {
        TeamItem(team = MockTeamEntity.getTeam1()) {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectTeamScreenSuccessPreview() {
    SelectTeamScreen(teams = MockTeams.getTeams().successState()) {
    }
}

@Preview(showBackground = true)
@Composable
fun SelectTeamScreenErrorPreview() {
    SelectTeamScreen(teams = errorState()) {
    }
}