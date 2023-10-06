package cr.o.cdc.tucancha.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cr.o.cdc.tucancha.R
import cr.o.cdc.tucancha.ui.theme.TuCanchaTheme

@Composable
fun ErrorCompose(onErrorClick: (() -> Unit?)? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.red)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            fontSize = 30.sp,
            modifier = Modifier.padding(10.dp),
            color = colorResource(id = R.color.white),
            text = stringResource(id = R.string.error)
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = {
                onErrorClick?.invoke()
            }
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                fontSize = 20.sp,
                color = colorResource(id = R.color.white),
                text = stringResource(id = R.string.retry)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorComposePreview() {
    TuCanchaTheme {
        ErrorCompose()
    }
}