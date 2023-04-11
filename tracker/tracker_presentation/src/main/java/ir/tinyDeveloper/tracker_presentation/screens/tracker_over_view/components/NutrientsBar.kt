package ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import ir.tinyDeveloper.core_ui.CarbColor
import ir.tinyDeveloper.core_ui.FatColor
import ir.tinyDeveloper.core_ui.LocalSizing
import ir.tinyDeveloper.core_ui.ProteinColor

@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    caloriesGoal: Int,
    modifier: Modifier
) {
    val background = MaterialTheme.colorScheme.background
    val caloriesExceedColor = MaterialTheme.colorScheme.error

    val carbsWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbsWidthRatio){
        carbsWidthRatio.animateTo(targetValue = ((carbs * 4f) / caloriesGoal))
    }
    LaunchedEffect(key1 = proteinWidthRatio){
        proteinWidthRatio.animateTo(((protein * 4f) / caloriesGoal))
    }
    LaunchedEffect(key1 = fatWidthRatio){
        fatWidthRatio.animateTo(((fat * 9f) / caloriesGoal))
    }

    val sizing = LocalSizing.current
    Canvas(modifier = modifier){
        if (calories <= caloriesGoal){
            val carbsWidth = carbsWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width
            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = sizing.nutrientsBarCornerRadius
            )
            drawRoundRect(
                color = FatColor,
                size = Size(width = carbsWidth + proteinWidth + fatWidth, height = size.height),
                cornerRadius = sizing.nutrientsBarCornerRadius
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(width = carbsWidth + proteinWidth, height = size.height),
                cornerRadius = sizing.nutrientsBarCornerRadius
            )
            drawRoundRect(
                color = CarbColor,
                size = Size(width = carbsWidth, height = size.height),
                cornerRadius = sizing.nutrientsBarCornerRadius
            )
        } else{
            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = sizing.nutrientsBarCornerRadius
            )
        }
    }
}