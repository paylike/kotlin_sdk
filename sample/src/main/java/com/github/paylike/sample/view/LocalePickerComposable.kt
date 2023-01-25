package com.github.paylike.sample.view

import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.supportedLocaleList
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun LocalePicker(
    modifier: Modifier = Modifier,
) {
    val timeToOpen = 500
    var angle by remember { mutableStateOf(90f) }
    val rotate by
        animateFloatAsState(
            targetValue = angle,
            tween(timeToOpen),
        )
    var expanded by remember { mutableStateOf(false) }
    val currentAppLocales: LocaleList =
        LocalContext.current.getSystemService(LocaleManager::class.java).applicationLocales
    val locale =
        if (currentAppLocales.isEmpty) {
            supportedLocaleList[4]
        } else {
            currentAppLocales[0]
        }
    var selectedLocale by remember { mutableStateOf(locale) }
    LocalContext.current.getSystemService(LocaleManager::class.java).applicationLocales =
        LocaleList(selectedLocale)

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
                    text = selectedLocale.displayName,
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
        for (i in 0 until supportedLocaleList.size()) {
            DropdownMenuItem(
                onClick = {
                    selectedLocale = supportedLocaleList.get(i)!!
                    expanded = false
                    angle = 90f
                }
            ) {
                Text(
                    text = supportedLocaleList.get(i)!!.displayName,
                    style = MaterialTheme.typography.button,
                )
            }
        }
    }
}
