package com.unibo.ui.compose.common
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeteoSaverAppBar(
    title: String,
) {
    CenterAlignedTopAppBar(
    title = { Text(text = title,
        style = TextStyle(
            fontSize = 26.sp,
                    )
                )
            },
    colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary
        ),
    )
}


@Preview
@Composable
fun MeteoSaverAppBarPreview() {
        MeteoSaverAppBar(
            title = "MeteoSaver",
        )
    }
