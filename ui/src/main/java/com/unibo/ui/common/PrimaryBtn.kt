package com.unibo.ui.common

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryBtn (
    btnName: String,
    onBtnClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    ElevatedButton(
        onClick = onBtnClick,
        modifier = modifier,
    ) {
        Text(
            text = btnName,
            fontSize = fontSize
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PrimaryBtnPreview() {
    PrimaryBtn(
        btnName = "Primary Button",
        onBtnClick = {}
    )
}
