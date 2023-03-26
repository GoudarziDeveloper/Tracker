package ir.tinyDeveloper.trackermultimodulearchitecture

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.tinyDeveloper.onboarding_presentation.screens.gender.GenderScreen
import ir.tinyDeveloper.core.navigation.Routes
import ir.tinyDeveloper.onboarding_presentation.screens.age.AgeScreen
import ir.tinyDeveloper.onboarding_presentation.welcome_screen.WelcomeScreen
import ir.tinyDeveloper.trackermultimodulearchitecture.navigation.navigate
import ir.tinyDeveloper.trackermultimodulearchitecture.ui.theme.TrackerMultiModuleArchitectureTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                            NavHost(navController = navController, startDestination = Routes.WELCOME){
                                composable(route = Routes.WELCOME){
                                    WelcomeScreen(onNavigate = navController::navigate)
                                }
                                composable(route = Routes.AGE){
                                    AgeScreen(
                                        onNavigate = navController::navigate,
                                        snackBarState = snackBarState
                                    )
                                }
                                composable(route = Routes.ACTIVITY) {

                                }
                                composable(route = Routes.GENDER){
                                    GenderScreen(onNavigate = navController::navigate)
                                }
                                composable(route = Routes.GOAL){

                                }
                                composable(route = Routes.HEIGHT){

                                }
                                composable(route = Routes.WEIGHT){

                                }
                                composable(route = Routes.NUTRIENT_GOAL){

                                }
                                composable(route = Routes.TRACKER_OVERVIEW){

                                }
                                composable(route = Routes.SEARCH){

                                }
                            }
                        }
                    )
                }
            }
        }
    }
}