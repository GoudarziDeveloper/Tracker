package ir.tinyDeveloper.onboarding_presentation.welcome_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ir.tinyDeveloper.onboarding_presentation.components.ActionButton
import ir.tinyDeveloper.core.R
import ir.tinyDeveloper.core.navigation.Routes
import ir.tinyDeveloper.core.util.UiEvent
import ir.tinyDeveloper.core_ui.LocalSpacing

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
){
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize().padding(LocalSpacing.current.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.next),
            onClick = { onNavigate(UiEvent.Navigate(route = Routes.GENDER)) }
        )
    }
}