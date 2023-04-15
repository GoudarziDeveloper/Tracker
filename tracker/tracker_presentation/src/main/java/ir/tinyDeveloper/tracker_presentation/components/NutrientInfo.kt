package ir.tinyDeveloper.tracker_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun NutrientInfo(
    name: String,
    amount: Int,
    unit: String,
    modifier: Modifier = Modifier,
    amountTextSize: TextUnit = MaterialTheme.typography.bodyLarge.fontSize,
    unitTextSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize,
    nameTextStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        UnitDisplay(
            amount = amount,
            unit = unit,
            amountTextSize = amountTextSize,
            unitTextSize = unitTextSize
        )
        Text(
            text = name,
            style = nameTextStyle,
            fontWeight = FontWeight.Light
        )
    }
}