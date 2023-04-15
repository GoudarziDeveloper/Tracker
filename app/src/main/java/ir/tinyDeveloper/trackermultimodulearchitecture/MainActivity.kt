package ir.tinyDeveloper.trackermultimodulearchitecture

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ir.tinyDeveloper.onboarding_presentation.screens.gender.GenderScreen
import ir.tinyDeveloper.core.navigation.Routes
import ir.tinyDeveloper.core_ui.LocalUiConstants
import ir.tinyDeveloper.onboarding_presentation.screens.activity.ActivityScreen
import ir.tinyDeveloper.onboarding_presentation.screens.age.AgeScreen
import ir.tinyDeveloper.onboarding_presentation.screens.goal.GoalScreen
import ir.tinyDeveloper.onboarding_presentation.screens.height.HeightScreen
import ir.tinyDeveloper.onboarding_presentation.screens.nutrient_goal.NutrientGoalScreen
import ir.tinyDeveloper.onboarding_presentation.screens.weight.WeightScreen
import ir.tinyDeveloper.onboarding_presentation.welcome_screen.WelcomeScreen
import ir.tinyDeveloper.tracker_presentation.screens.search.SearchScreen
import ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.TrackerOverviewScreen
import ir.tinyDeveloper.trackermultimodulearchitecture.navigation.navigate
import ir.tinyDeveloper.trackermultimodulearchitecture.ui.theme.TrackerMultiModuleArchitectureTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiConstants = LocalUiConstants.current
            TrackerMultiModuleArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val snackBarState = remember { SnackbarHostState() }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = { SnackbarHost(snackBarState) },
                        content = {
                            NavHost(
                                navController = navController,
                                startDestination = Routes.WELCOME
                            ) {
                                composable(route = Routes.WELCOME) {
                                    WelcomeScreen(onNavigate = navController::navigate)
                                }
                                composable(route = Routes.AGE) {
                                    AgeScreen(
                                        onNavigate = navController::navigate,
                                        snackBarState = snackBarState
                                    )
                                }
                                composable(route = Routes.ACTIVITY) {
                                    ActivityScreen(onNavigate = navController::navigate)
                                }
                                composable(route = Routes.GENDER) {
                                    GenderScreen(onNavigate = navController::navigate)
                                }
                                composable(route = Routes.GOAL) {
                                    GoalScreen(onNavigate = navController::navigate)
                                }
                                composable(route = Routes.HEIGHT) {
                                    HeightScreen(
                                        onNavigate = navController::navigate,
                                        snackBarState = snackBarState
                                    )
                                }
                                composable(route = Routes.WEIGHT) {
                                    WeightScreen(
                                        onNavigate = navController::navigate,
                                        snackBarState = snackBarState
                                    )
                                }
                                composable(route = Routes.NUTRIENT_GOAL) {
                                    NutrientGoalScreen(
                                        onNavigate = navController::navigate,
                                        snackBarState = snackBarState
                                    )
                                }
                                composable(route = Routes.TRACKER_OVERVIEW) {
                                    TrackerOverviewScreen(onNavigate = navController::navigate)
                                }
                                composable(
                                    route = Routes.SEARCH +
                                            "/{${uiConstants.mealNameArgument}}/{${uiConstants.dayOfMonthArgument}}/{${uiConstants.monthArgument}}/{${uiConstants.yearArgument}}",
                                    arguments = listOf(
                                        navArgument(name = uiConstants.mealNameArgument) {
                                            type = NavType.StringType
                                        },
                                        navArgument(name = uiConstants.dayOfMonthArgument) {
                                            type = NavType.IntType
                                        },
                                        navArgument(name = uiConstants.monthArgument) {
                                            type = NavType.IntType
                                        },
                                        navArgument(name = uiConstants.yearArgument) {
                                            type = NavType.IntType
                                        }
                                    )
                                ) {
                                    val mealName =
                                        it.arguments?.getString(uiConstants.mealNameArgument)!!
                                    val dayOfMonth =
                                        it.arguments?.getInt(uiConstants.dayOfMonthArgument)!!
                                    val month = it.arguments?.getInt(uiConstants.monthArgument)!!
                                    val year = it.arguments?.getInt(uiConstants.yearArgument)!!
                                    SearchScreen(
                                        snackBarState = snackBarState,
                                        onNavigateUp = navController::navigateUp,
                                        mealName = mealName,
                                        dayOfMonth = dayOfMonth,
                                        month = month,
                                        year = year
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}