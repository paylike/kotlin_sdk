package com.github.paylike.sample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.paylike.sample.ui.theme.Kotlin_sdkTheme
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask
import com.steliospapamichail.creditcardmasker.viewtransformations.ExpirationDateMask
import org.intellij.lang.annotations.JdkConstants

class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteLabelDemo()
        }
    }
}


val paylikeGreen= Color(0xFF2e8f29)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun WhiteLabelDemo() {
    Kotlin_sdkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(backgroundColor = paylikeGreen) {
                        Row(Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Text(
                                "Minimal white label demo",
                                color = Color(0xFFFFFFFF),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                            )
                        }
                    }
                },
                content = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize().padding(0.dp)
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CardNumber()
                            val image = painterResource(R.drawable.ic_visa_icon)
                            Image(
                                painter = image,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                            val image2 = painterResource(R.drawable.ic_mastercard_icon)

                            Image(
                                painter = image2,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Expiration()
                            SecurityCode()
                        }
                    }


                }
            )
        }
    }
}


@Composable
fun CardNumber() {
    var number by remember { mutableStateOf("") }
    TextField(
        value = number,
        visualTransformation = CardNumberMask(),
        modifier = Modifier
            .fillMaxWidth(0.6f),
        onValueChange = {
            if (it.length <= 16) number  = it
        }
    )

@Composable
fun Expiration() {
    var expiration by remember { mutableStateOf("") }
    OutlinedTextField(
        value = expiration,
        //  visualTransformation = ExpirationDateMask(),
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .height(100.dp),

        onValueChange = {
            if (it.length <= 4) expiration = it
        }, label = { Text("Expiration") }
    )
}

@Composable
fun SecurityCode() {
    var securityCode by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .height(100.dp),
        value = securityCode,
        onValueChange = {
            if (it.length <= 3) securityCode = it
        }, label = { Text("Security Code                                                                                                                                                                                                                                                                    ") }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Kotlin_sdkTheme {
        WhiteLabelDemo()
    }
}
