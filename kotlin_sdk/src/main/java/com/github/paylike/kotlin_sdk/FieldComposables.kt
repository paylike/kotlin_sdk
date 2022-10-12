package com.github.paylike.kotlin_sdk.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.paylike.kotlin_sdk.cardprovider.CardProviderIconMap
import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders
import com.github.paylike.kotlin_sdk.theme.PaylikeErrorRed
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask
import com.steliospapamichail.creditcardmasker.viewtransformations.ExpirationDateMask

@Composable
fun CardNumber(
    number: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
) {
    TextField(
        placeholder = {
            Text(
                text = "0000 0000 0000 0000",
                color = (if (isValid) Color.Gray else PaylikeErrorRed),
                fontSize = 18.sp
            )
        },
        value = number,
        visualTransformation = CardNumberMask(),
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else PaylikeErrorRed,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun CardProviderImage(highlightedCardBrand: SupportedCardProviders) {
    if (highlightedCardBrand != SupportedCardProviders.NONE &&
        CardProviderIconMap[highlightedCardBrand] != null) {
        Image(
            painter = painterResource(CardProviderIconMap[highlightedCardBrand]!!),            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(horizontal = 8.dp),
        )
    } else {
        Box(modifier = Modifier
            .size(48.dp)
            .padding(horizontal = 8.dp),
        )
    }
}

@Composable
fun Expiration(
    date: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        placeholder = {
            Text(
                text = "MM/YY",
                color = (if (isValid) Color.Gray else PaylikeErrorRed),
                fontSize = 18.sp
            )
        },
        value = date,
        visualTransformation = ExpirationDateMask(),
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else PaylikeErrorRed,
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
    modifier: Modifier
) {
    TextField(
        placeholder = {
            Text(
                text = "XXX",
                color = (if (isValid) Color.Gray else PaylikeErrorRed),
                fontSize = 18.sp
            )
        },
        modifier = modifier,
        value = securityCode,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else PaylikeErrorRed,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun FirstNameInput(
    firstName: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
) {
    TextField(
        placeholder = {
            Text(
                text = "John",
                color = (if (isValid) Color.Gray else PaylikeErrorRed),
                fontSize = 18.sp
            )
        },
        value = firstName,
        modifier = modifier,
        onValueChange = onValueChanged,
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else PaylikeErrorRed,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun LastNameInput(
    lastName: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
) {
    TextField(
        placeholder = {
            Text(
                text = "Doe",
                color = (if (isValid) Color.Gray else PaylikeErrorRed),
                fontSize = 18.sp
            )
        },
        value = lastName,
        modifier = modifier,
        onValueChange = onValueChanged,
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else PaylikeErrorRed,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun NoteInput(
    note: String,
    onValueChanged: (String) -> Unit,
) {
    TextField(
        placeholder = {
            Text(
                text = "Note (optional)",
                fontSize = 18.sp
            )
        },
        value = note,
        onValueChange = onValueChanged,
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
