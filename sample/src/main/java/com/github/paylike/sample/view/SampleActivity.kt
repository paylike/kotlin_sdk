package com.github.paylike.sample.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.PaylikeGreen
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.PaylikeWhite
import com.github.paylike.sample.viewmodel.SampleViewModel

class SampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model: SampleViewModel by viewModels()
        setContent { SampleComposable(model) }
    }
}

@Composable
private fun SampleComposable(viewModel: SampleViewModel) {
    PaylikeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {},
                content = { padding ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize().padding(padding)
                    ) {}
                }
            )
        }
    }
}

@Composable
fun TopBarContent() {
    TopAppBar(backgroundColor = PaylikeGreen) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                "Simple White Label",
                color = PaylikeWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
    }
}
