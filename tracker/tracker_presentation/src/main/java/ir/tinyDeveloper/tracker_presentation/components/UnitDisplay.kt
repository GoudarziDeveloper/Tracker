package ir.tinyDeveloper.tracker_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import ir.tinyDeveloper.core_ui.LocalSpacing

@Composable
fun UnitDisplay(
    amount: Int,
    unit: String,
    modifier: Modifier = Modifier,
    amountTextSize: TextUnit = MaterialTheme.typography.bodyLarge.fontSize,
    unitTextSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize
) {
    Row(modifier = modifier) {
        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.alignBy(LastBaseline),
            fontSize = amountTextSize
        )
        Spacer(modifier = Modifier.width(LocalSpacing.current.spaceExtraSmall))
        Text(
            text = unit,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.alignBy(LastBaseline),
            fontSize = unitTextSize
        )
    }
}