package com.unibo.ui.compose.common
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Usiamo @OptIn per dire che siamo consapevoli di usare API sperimentali di Material 3
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeteoSaverAppBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = navigationIcon,
        actions = { actions() },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

// Creiamo anche una preview per la nostra TopAppBar isolata!
@Preview
@Composable
fun MeteoSaverAppBarPreview() {
        MeteoSaverAppBar(
            title = "MeteoSaver",
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon"
                    )
                }
            }
        )
    }
