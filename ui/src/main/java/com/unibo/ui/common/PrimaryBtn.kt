package com.unibo.ui.common

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryBtn (
    btnName: String,
    onBtnClick: () -> Unit,
) {
    ElevatedButton(onClick = {onBtnClick() }) {
        Text(btnName)
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
