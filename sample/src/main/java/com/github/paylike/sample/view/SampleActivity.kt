package com.github.paylike.sample.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.WhiteLabelFormComposable
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.*
import com.github.paylike.kotlin_sdk.viewmodel.BasicViewModel
import com.github.paylike.sample.viewmodel.SampleViewModel

class SampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.hide()

        val model: BasicViewModel by viewModels()

        setContent { WhiteLabelFormComposable(model) }
    }
}

@Composable
private fun SampleComposable(viewModel: SampleViewModel) {
    PaylikeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBarContent() },
            content = { padding -> Surface(modifier = Modifier.fillMaxSize().padding(padding)) {} }
        )
    }
}

@Composable
fun TopBarContent() {
    TopAppBar(
        backgroundColor = PaylikeMaterialTheme.colors.primary,
        contentColor = PaylikeMaterialTheme.colors.onPrimary,
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = {
                    Text(
                        text = "Paylike Payment Forms Demo",
                        style = PaylikeMaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                    )
                },
            )
        },
    )
}
