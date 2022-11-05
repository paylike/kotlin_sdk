package com.github.paylike.sample.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.PaymentTestDto
import com.github.paylike.sample.viewmodel.errorCases

@Composable
fun ErrorCasePicker(
    modifier: Modifier = Modifier,
    errorCase: Pair<String, PaymentTestDto>,
    onErrorCaseChanged: (Pair<String, PaymentTestDto>) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
    ) {
        Button(
            modifier = Modifier.height(50.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
            onClick = { expanded = true },
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier,
                    text = errorCase.first,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.button
                )
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = ""
                )
            }
        }
        DropdownMenu(
            modifier = Modifier.requiredHeightIn(0.dp, 300.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            errorCases.forEach { (key, value) ->
                DropdownMenuItem(
                    onClick = {
                        onErrorCaseChanged(Pair(key, value))
                        expanded = false
                    }
                ) {
                    Text(
                        text = key,
                        style = MaterialTheme.typography.button,
                    )
                }
            }
        }
    }
}
