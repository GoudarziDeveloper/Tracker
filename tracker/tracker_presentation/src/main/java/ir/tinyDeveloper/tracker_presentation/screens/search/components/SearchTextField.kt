package ir.tinyDeveloper.tracker_presentation.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import ir.tinyDeveloper.core.R
import ir.tinyDeveloper.core_ui.LocalSizing
import ir.tinyDeveloper.core_ui.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.search),
    shouldShowHint: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
    val spacing = LocalSpacing.current
    val sizing = LocalSizing.current

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardActions = KeyboardActions(onSearch = {
                onSearch()
                defaultKeyboardAction(ImeAction.Search)
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Light
            ),
            cursorBrush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.colorScheme.onSurface,
                )
            ),
            modifier = Modifier
                .clip(sizing.searchTextFieldCornerRadius)
                .padding(sizing.searchTextFieldPadding)
                .shadow(
                    elevation = sizing.searchTextFieldPadding,
                    shape = sizing.searchTextFieldCornerRadius
                )
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
                .padding(end = spacing.spaceMedium)
                .onFocusChanged { onFocusChange(it) }
        )
        if (shouldShowHint) {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = spacing.spaceMedium)
            )
        }
        IconButton(onClick = { onSearch() }, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search)
            )
        }
    }
}