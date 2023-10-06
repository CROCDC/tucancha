package cr.o.cdc.tucancha.mocks

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import cr.o.cdc.tucancha.networking.Resource

@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun <T> T.toState() = produceState(initialValue = this) {

}

@Composable
fun <T> T.successState(): State<Resource<T>> = Resource.Success(this).toState()

@Composable
fun <T> errorState(): State<Resource<T>> = Resource.Error<T>(null,null).toState()