package com.github.paylike.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.paylike.sample.ui.theme.Kotlin_sdkTheme
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask

class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kotlin_sdkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WhiteLabelDemo()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun WhiteLabelDemo() {
    CardNumber()
    val visaIcon = AnimatedImageVector.animatedVectorResource(R.drawable.visa_icon)

    Icon(
        painter = rememberAnimatedVectorPainter(visaIcon, false),
        contentDescription = null
    )
}


@Composable
fun CardNumber() {
    var number by remember { mutableStateOf("") }
    TextField(
        value = number,
        visualTransformation = CardNumberMask(),
        onValueChange = {
            if (it.length <= 16) number  = it
        }
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Kotlin_sdkTheme {
        WhiteLabelDemo()
    }
}
