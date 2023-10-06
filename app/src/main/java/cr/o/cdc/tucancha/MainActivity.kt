package cr.o.cdc.tucancha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cr.o.cdc.tucancha.screens.SelectPlayerRoute
import cr.o.cdc.tucancha.screens.SelectTeamRoute
import cr.o.cdc.tucancha.screens.TuCanchaScreen
import cr.o.cdc.tucancha.ui.theme.TuCanchaTheme
import cr.o.cdc.tucancha.vm.TuCanchaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TuCanchaApp()
        }
    }

    companion object {
        const val selectTeamRoute = "selectTeam"
        const val selectPlayerRoute = "selectPlayer/"
        const val tuCanchaRoute = "tuCancha"
    }
}

@Composable
fun TuCanchaApp(navController: NavHostController = rememberNavController()) {
    val vm = viewModel<TuCanchaViewModel>()
    TuCanchaTheme {
        NavHost(
            navController = navController,
            MainActivity.tuCanchaRoute
        ) {
            composable(MainActivity.tuCanchaRoute) {
                TuCanchaScreen(
                    vm.addedPlayers.collectAsState(
                        initial = listOf()
                    ),
                    onAddPlayerClick = {
                        navController.navigate(MainActivity.selectTeamRoute)
                    },
                    onDragFinish = { id, x, y ->
                        vm.movePlayer(id, x, y)
                    },
                    removePlayer = {
                        vm.removePlayer(it)
                    }
                )
            }
            composable(MainActivity.selectTeamRoute) {
                SelectTeamRoute {
                    navController.navigate(MainActivity.selectPlayerRoute + it)
                }
            }
            composable(MainActivity.selectPlayerRoute + "{teamId}") { navStack ->
                SelectPlayerRoute(
                    onClickPlayerRow = {
                        vm.addPlayer(it)
                        navController.navigate(MainActivity.tuCanchaRoute)
                    },
                    teamId = checkNotNull(navStack.arguments?.getString("teamId"))
                )
            }
        }
    }
}

