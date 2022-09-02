package com.github.paylike.sample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Button
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.KeyboardType
import com.github.paylike.sample.ui.theme.Kotlin_sdkTheme
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask
import com.steliospapamichail.creditcardmasker.viewtransformations.ExpirationDateMask

class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteLabelDemo()
        }
    }
}

enum class cardBrands {
    NONE, MASTERCARD, VISA
}

val paylikeGreen= Color(0xFF2e8f29)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun WhiteLabelDemo() {
    var cardNumber by remember { mutableStateOf("") }
    var highlightedCardBrand by remember { mutableStateOf(cardBrands.NONE) }
    var expiryDate by remember { mutableStateOf("") }
    var securityCode by remember { mutableStateOf("") }

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
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CardNumber(
                                cardNumber,
                                {
                                    if (it.length <= 16) cardNumber = it
                                    if (it.isEmpty()) {
                                        highlightedCardBrand = cardBrands.NONE
                                        return@CardNumber
                                    }
                                    highlightedCardBrand = if (it[0]?.digitToIntOrNull() == 4)
                                        cardBrands.VISA
                                    else if (it[0]?.digitToIntOrNull() == 5)
                                        cardBrands.MASTERCARD
                                    else cardBrands.NONE
                                },
                                Modifier.weight(55f)
                            )
                            val image = painterResource(R.drawable.ic_mastercard_icon)
                            val image2 = painterResource(R.drawable.ic_visa_icon)
                            Row (
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.weight(45f)
                            )
                            {
                                Image(
                                    painter = image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(horizontal = 8.dp),
                                    colorFilter = if (highlightedCardBrand == cardBrands.VISA)
                                        ColorFilter.tint(Color.LightGray) else null
                                )
                                Image(
                                    painter = image2,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(horizontal = 8.dp),
                                    colorFilter = if (highlightedCardBrand == cardBrands.MASTERCARD)
                                        ColorFilter.tint(Color.LightGray) else null
                                )
                            }
                        }
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Expiration(
                                expiryDate,
                                { if (it.length <= 4) expiryDate = it },
                                Modifier.weight(2f))
                            SecurityCode(
                                securityCode,
                                { if (it.length <= 3) securityCode = it },
                                Modifier.weight(1f))
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = paylikeGreen,
                                contentColor = androidx.compose.ui.graphics.Color.White
                            ),
                            onClick = {},
                            modifier = Modifier.size(100.dp, 32.dp)
                        ) {
                            Text("Pay", fontSize = 12.sp)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun CardNumber(
    number: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier, )
{
    TextField(
        placeholder = { Text(text = "0000 0000 0000 0000") },
        value = number,
        visualTransformation = CardNumberMask(),
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

    )
}

@Composable
fun Expiration(
    date: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier) {
    TextField(
        placeholder = { Text(text = "MM/YY") },
        value = date,
        visualTransformation = ExpirationDateMask(),
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SecurityCode(
    securityCode: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier)
{
    TextField(
        placeholder = { Text(text = "XXX") },
        modifier = modifier,
        value = securityCode,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Kotlin_sdkTheme {
        WhiteLabelDemo()
    }
}
