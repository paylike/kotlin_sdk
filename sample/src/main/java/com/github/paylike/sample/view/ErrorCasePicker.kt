package com.github.paylike.sample.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.PaymentTestDto
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.sample.viewmodel.errorCases

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ErrorCasePicker(
    modifier: Modifier = Modifier,
    errorCase: Pair<String, PaymentTestDto>,
    onErrorCaseChanged: (Pair<String, PaymentTestDto>) -> Unit,
) {
    val timeToOpen = 500
    var angle by remember { mutableStateOf(90f) }
    val rotate by
        animateFloatAsState(
            targetValue = angle,
            tween(timeToOpen),
        )
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier =
            modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(
                    shape =
                        CutCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp,
                        )
                ),
        backgroundColor = PaylikeTheme.colors.primary,
        onClick = {
            expanded = true
            angle = 0f
        },
        content = {
            Row(
                modifier = Modifier.fillMaxSize().padding(PaylikeTheme.paddings.smallPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier,
                    text = errorCase.first,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.button,
                    fontSize = TextUnit.Unspecified
                )
                Icon(
                    modifier = Modifier.rotate(rotate),
                    imageVector = Icons.Rounded.ArrowDropDown,
                    contentDescription = ""
                )
            }
        }
    )
    DropdownMenu(
        modifier =
            Modifier.requiredHeightIn(0.dp, 300.dp)
                .background(color = PaylikeTheme.colors.secondary),
        expanded = expanded,
        onDismissRequest = {
            expanded = false
            angle = 90f
        }
    ) {
        errorCases.forEach { (key, value) ->
            DropdownMenuItem(
                onClick = {
                    onErrorCaseChanged(Pair(key, value))
                    expanded = false
                    angle = 90f
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
