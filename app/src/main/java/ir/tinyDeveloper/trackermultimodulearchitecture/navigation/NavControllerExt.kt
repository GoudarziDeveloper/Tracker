package ir.tinyDeveloper.trackermultimodulearchitecture.navigation

import androidx.navigation.NavController
import ir.tinyDeveloper.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate){
    this.navigate(event.route)
}