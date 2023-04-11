package ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ir.tinyDeveloper.core.R
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DaySelector(
    date: LocalDate,
    onPreviousDayClicked: () -> Unit,
    onNextDayClicked: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousDayClicked) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.previous_day)
            )
        }
        Text(text = parseDateText(date = date), style = MaterialTheme.typography.titleMedium)
        IconButton(onClick = onNextDayClicked) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = stringResource(id = R.string.next_day)
            )
        }
    }
}