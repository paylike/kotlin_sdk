package com.github.paylike.sample.view

import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.supportedLocaleList

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun LocalePicker(
    modifier: Modifier = Modifier,
) {
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
                    text = selectedLocale.displayName,
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
            for (i in 0 until supportedLocaleList.size()) {
                DropdownMenuItem(
                    onClick = {
                        selectedLocale = supportedLocaleList.get(i)!!
                        expanded = false
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
}
