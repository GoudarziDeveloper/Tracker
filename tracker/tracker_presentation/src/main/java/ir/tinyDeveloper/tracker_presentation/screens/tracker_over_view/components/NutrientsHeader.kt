package ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import ir.tinyDeveloper.core.R
import ir.tinyDeveloper.core_ui.CarbColor
import ir.tinyDeveloper.core_ui.FatColor
import ir.tinyDeveloper.core_ui.LocalSizing
import ir.tinyDeveloper.core_ui.LocalSpacing
import ir.tinyDeveloper.core_ui.ProteinColor
import ir.tinyDeveloper.tracker_presentation.components.UnitDisplay
import ir.tinyDeveloper.tracker_presentation.model.TrackerOverviewState

@Composable
fun NutrientsHeader(
    state: TrackerOverviewState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val sizing = LocalSizing.current
    val animatedCalorieCount = animateIntAsState(targetValue = state.totalCalories)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = sizing.nutrientsHeaderBottomRadius,
                    bottomEnd = sizing.nutrientsHeaderBottomRadius
                )
            )
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = spacing.spaceLarge, vertical = spacing.spaceExtraLarge)
        )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = stringResource(R.string.kcal),
                amountTextSize = sizing.nutrientsHeaderCaloriesAmountTextSize,
                modifier = Modifier.align(Alignment.Bottom)
            )
            Column {
                Text(
                    text = stringResource(id = R.string.your_goal),
                    style = MaterialTheme.typography.bodySmall
                )
                UnitDisplay(
                    amount = animatedCalorieCount.value,
                    unit = stringResource(R.string.kcal),
                    amountTextSize = sizing.nutrientsHeaderCaloriesAmountTextSize
                )
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        NutrientsBar(
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            fat = state.totalFat,
            calories = state.totalCalories,
            caloriesGoal = state.caloriesGoal,
            modifier = Modifier
                .fillMaxWidth()
                .height(sizing.nutrientsBarHeight)
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            NutrientBarInfo(
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(id = R.string.carbs),
                color = CarbColor,
                modifier = Modifier.size(sizing.nutrientBatInfoSize)
            )
            NutrientBarInfo(
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(id = R.string.protein),
                color = ProteinColor,
                modifier = Modifier.size(sizing.nutrientBatInfoSize)
            )
            NutrientBarInfo(
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(id = R.string.fat),
                color = FatColor,
                modifier = Modifier.size(sizing.nutrientBatInfoSize)
            )
        }
    }
}