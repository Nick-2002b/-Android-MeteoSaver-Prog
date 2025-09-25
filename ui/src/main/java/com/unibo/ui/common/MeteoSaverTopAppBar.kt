package com.unibo.ui.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.unibo.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeteoSaverAppBar(
    title: String,
    actions: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
) {
    CenterAlignedTopAppBar(
    title = { Text(text = title,
        style = TextStyle(
            fontSize = integerResource(id = R.integer.app_name_size).sp,
                    )
                )
            },
        navigationIcon = navigationIcon,
        actions ={ actions() },
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
