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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.paylike.kotlin_luhn.PaylikeLuhn
import com.github.paylike.sample.CardBrands
import com.github.paylike.sample.R
import com.github.paylike.sample.ui.theme.PaylikeGreen
import com.github.paylike.sample.ui.theme.Kotlin_sdkTheme
import com.github.paylike.sample.ui.theme.PaylikeWhite
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WhiteLabelDemo() {
    // props - could be refactored somehow to make this look better
    var cardNumber by remember { mutableStateOf("") }
    var isCardNumberValid by remember { mutableStateOf(true) }

    var expiryDate by remember { mutableStateOf("") }
    var isExpiryDateValid by remember { mutableStateOf(true) }

    var securityCode by remember { mutableStateOf("") }
    var isSecurityCodeValid by remember { mutableStateOf(true) }

    var highlightedCardBrand by remember { mutableStateOf(CardBrands.NONE) }

    Kotlin_sdkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = {
                    TopBarContent()
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
                                isCardNumberValid,
                                {
                                    isCardNumberValid = true
                                    if (it.length <= 16) cardNumber = it
                                    if (it.isEmpty()) {
                                        highlightedCardBrand = CardBrands.NONE
                                        return@CardNumber
                                    }
                                    highlightedCardBrand = highlightDetectedCardBrand(it)
                                },
                                Modifier.weight(55f)
                            )
                            Row (
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.weight(45f)
                            )
                            {
                                MasterCardImage(highlightedCardBrand)
                                VisaImage(highlightedCardBrand)
                            }
                        }
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Expiration(
                                expiryDate,
                                isExpiryDateValid,
                                {
                                    isExpiryDateValid = true
                                    if (it.length <= 4) expiryDate = it
                                },
                                Modifier.weight(2f))
                            SecurityCode(
                                securityCode,
                                isSecurityCodeValid,
                                {
                                    isSecurityCodeValid = true
                                    if (it.length <= 3) securityCode = it
                                },
                                Modifier.weight(1f))
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = PaylikeGreen,
                                contentColor = PaylikeWhite
                            ),
                            onClick = {
                                if (cardNumber.length < 16 ||
                                    !PaylikeLuhn.isValid(cardNumber))
                                    isCardNumberValid = false
                                if (expiryDate.length < 4 ||
                                    expiryDate.substring(0, 2).toIntOrNull()!! > 12)
                                    isExpiryDateValid = false
                                if (securityCode.length < 3)
                                    isSecurityCodeValid = false
                            },
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
private fun TopBarContent() {
    TopAppBar(backgroundColor = PaylikeGreen) {
        Row(Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                "Minimal white label demo",
                color = PaylikeWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
fun CardNumber(
    number: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
)
{
    TextField(
        placeholder = { Text(text = "0000 0000 0000 0000") },
        value = number,
        visualTransformation = CardNumberMask(),
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else Color.Red,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun VisaImage(highlightedCardBrand: CardBrands) {
    Image(
        painter = painterResource(R.drawable.ic_visa_icon),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .padding(horizontal = 8.dp),
        colorFilter = if (highlightedCardBrand == CardBrands.MASTERCARD)
            ColorFilter.tint(Color.LightGray) else null
    )
}

@Composable
private fun MasterCardImage(highlightedCardBrand: CardBrands) {
    Image(
        painter = painterResource(R.drawable.ic_mastercard_icon),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .padding(horizontal = 8.dp),
        colorFilter = if (highlightedCardBrand == CardBrands.VISA)
            ColorFilter.tint(Color.LightGray) else null
    )
}


private fun highlightDetectedCardBrand(cardNumberInput: String): CardBrands {
    return if (cardNumberInput[0]?.digitToIntOrNull() == 4)
        CardBrands.VISA
    else if (cardNumberInput[0]?.digitToIntOrNull() == 5)
        CardBrands.MASTERCARD
    else CardBrands.NONE
}

@Composable
fun Expiration(
    date: String,
    isValid: Boolean,
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
            textColor = if (isValid) Color.Gray else Color.Red,
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
    isValid: Boolean,
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
            textColor = if (isValid) Color.Gray else Color.Red,
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
