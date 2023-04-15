package ir.tinyDeveloper.core_ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Spacing(
    val spaceDefault: Dp = 0.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp
)
val LocalSpacing = compositionLocalOf{ Spacing() }

data class Sizing(
    val actionButtonCornerRadius: Dp = 100.dp,
    val selectableButtonRadius: Dp = 100.dp,
    val nutrientsBarCornerRadius: CornerRadius = CornerRadius(100f),
    val nutrientBarInfo: Dp = 8.dp,
    val nutrientsHeaderBottomRadius: Dp = 50.dp,
    val nutrientsHeaderCaloriesAmountTextSize: TextUnit = 40.sp,
    val nutrientsBarHeight: Dp = 30.dp,
    val nutrientBatInfoSize: Dp = 90.dp,
    val addButtonShape: RoundedCornerShape = RoundedCornerShape(100),
    val addButtonShapeBorderWidth: Dp = 1.dp,
    val ExpandableMealNutrientsAmountTextSize: TextUnit = 30.sp,
    val trackedFoodItemShape: RoundedCornerShape = RoundedCornerShape(5.dp),
    val trackedFoodItemShadowElevation: Dp = 1.dp,
    val trackedFoodItemHeight: Dp = 100.dp,
    val trackedFoodItemAmountTextSize: TextUnit = 16.sp,
    val trackedFoodItemUnitTextSize: TextUnit = 12.sp,
    val searchTextFieldCornerRadius: RoundedCornerShape = RoundedCornerShape(5.dp),
    val searchTextFieldPadding: Dp = 2.dp
)
val LocalSizing = compositionLocalOf { Sizing() }

data class UiConstants(
    val mealNameArgument: String = "mealName",
    val dayOfMonthArgument: String = "dayOfMonth",
    val monthArgument: String = "month",
    val yearArgument: String = "year"

)
val LocalUiConstants = compositionLocalOf { UiConstants() }