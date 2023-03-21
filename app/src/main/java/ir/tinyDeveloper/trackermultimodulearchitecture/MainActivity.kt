package ir.tinyDeveloper.trackermultimodulearchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.tinyDeveloper.core.navigation.Routes
import ir.tinyDeveloper.onboarding_presentation.welcome_screen.WelcomeScreen
import ir.tinyDeveloper.trackermultimodulearchitecture.navigation.navigate
import ir.tinyDeveloper.trackermultimodulearchitecture.ui.theme.TrackerMultiModuleArchitectureTheme

class MainActivity : ComponentActivity() {
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
                    NavHost(navController = navController, startDestination = Routes.WELCOME){
                        composable(route = Routes.WELCOME){
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Routes.AGE){

                        }
                        composable(route = Routes.ACTIVITY) {

                        }
                        composable(route = Routes.GENDER){

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
            }
        }
    }
}