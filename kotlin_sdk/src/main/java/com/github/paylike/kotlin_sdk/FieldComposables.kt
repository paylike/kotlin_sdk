package com.github.paylike.kotlin_sdk

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.paylike.kotlin_sdk.cardprovider.CardProviderIconMap
import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders
import com.github.paylike.kotlin_sdk.theme.PaylikeErrorRed
import com.github.paylike.kotlin_sdk.theme.PaylikeMaterialTheme
import com.github.paylike.kotlin_sdk.visualformatter.CardNumberVisualTransformation
import com.github.paylike.kotlin_sdk.visualformatter.CardVerificationCodeVisualTransformation
import com.github.paylike.kotlin_sdk.visualformatter.ExpirationDateVisualTransformation

/**
 *
 */
@Composable
fun CardNumberField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isValid: Boolean,
    isEnabled: Boolean = true, // TODO
    onValueChanged: (String) -> Unit,
    highlightedCardProvider: SupportedCardProviders,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        trailingIcon = {
            CardProviderIcons(
                modifier = Modifier,
                highlightedCardProvider = highlightedCardProvider,
            )
        },
        visualTransformation = CardNumberVisualTransformation(
            validColor = PaylikeMaterialTheme.colors.onBackground,
            invalidColor = PaylikeMaterialTheme.colors.error,
            isValid = isValid,
        ),
        singleLine = true,
        enabled = isEnabled, // TODO if its good
        isError = !isValid, //TODO if its good
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal,),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = PaylikeMaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

/**
 *
 */
@Composable
fun ExpiryDateField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isValid: Boolean,
    isEnabled: Boolean = true, // TODO
    onValueChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        visualTransformation = ExpirationDateVisualTransformation(
            validColor = PaylikeMaterialTheme.colors.onBackground,
            invalidColor = PaylikeMaterialTheme.colors.error,
            isValid = isValid,
        ),
        singleLine = true,
        enabled = isEnabled, // TODO if its good
        isError = !isValid, //TODO if its good
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = PaylikeMaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

/**
 *
 */
@Composable
fun CardVerificationCodeField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isValid: Boolean,
    isEnabled: Boolean = true, // TODO
    onValueChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        visualTransformation = CardVerificationCodeVisualTransformation(
            validColor = PaylikeMaterialTheme.colors.onBackground,
            invalidColor = PaylikeMaterialTheme.colors.error,
            isValid = isValid,
        ),
        enabled = isEnabled, // TODO if its good
        isError = !isValid, //TODO if its good
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = PaylikeMaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

/**
 *
 */
@Composable
fun FirstNameField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isValid: Boolean,
    isEnabled: Boolean = true, // TODO
    onValueChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        placeholder = {
            Text(
                modifier = modifier,
                text = "John",
                style = textStyle,
                color = (if (isValid) Color.LightGray else PaylikeMaterialTheme.colors.error),
            )
        },
        enabled = isEnabled, // TODO if its good
        isError = !isValid, //TODO if its good
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = PaylikeMaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

/**
 *
 */
@Composable
fun LastNameField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isValid: Boolean,
    isEnabled: Boolean = true, // TODO
    onValueChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        placeholder = {
            Text(
                modifier = modifier,
                text = "Doe",
                style = textStyle,
                color = (if (isValid) Color.LightGray else PaylikeMaterialTheme.colors.error),
            )
        },
        enabled = isEnabled, // TODO if its good
        isError = !isValid, //TODO if its good
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = PaylikeMaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

/**
 *
 */
@Composable
fun NoteField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isEnabled: Boolean = true, // TODO
    onValueChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        placeholder = {
            Text(
                modifier = modifier,
                text = "Note (optional)",
                style = textStyle,
                color = Color.LightGray,
            )
        },
        enabled = isEnabled, // TODO if its good
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = PaylikeMaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

/**
 *
 */
@Composable
fun CardProviderIcon(
    modifier: Modifier = Modifier,
    showedCardProviderIcon: SupportedCardProviders,
    isHighlighted: Boolean,
) {
    Image(
        modifier = modifier,
        painter = painterResource(CardProviderIconMap[showedCardProviderIcon]!!),
        contentDescription = null,
        colorFilter = if (isHighlighted) null else ColorFilter.tint(Color.Gray),
    )
}

/**
 *
 */
@Composable
fun CardProviderIcons(
    modifier: Modifier = Modifier,
    highlightedCardProvider: SupportedCardProviders
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ){
        CardProviderIcon(
            modifier = Modifier
                .size(48.dp)
                .padding(horizontal = 8.dp),
            showedCardProviderIcon = SupportedCardProviders.MAESTRO,
            isHighlighted = highlightedCardProvider == SupportedCardProviders.MAESTRO,
        )
        CardProviderIcon(
            modifier = Modifier
                .size(48.dp)
                .padding(horizontal = 8.dp),
            showedCardProviderIcon = SupportedCardProviders.MASTERCARD,
            isHighlighted = highlightedCardProvider == SupportedCardProviders.MASTERCARD,
        )
        CardProviderIcon(
            modifier = Modifier
                .size(48.dp)
                .padding(horizontal = 8.dp),
            showedCardProviderIcon = SupportedCardProviders.VISA,
            isHighlighted = highlightedCardProvider == SupportedCardProviders.VISA,
        )
    }
}

/**
 *
 */
@Composable
fun PayButton(
    modifier: Modifier = Modifier,
    shape: Shape = PaylikeMaterialTheme.shapes.medium,
    onClick: () -> Unit,
    isVisible: Boolean = true,
    content: @Composable RowScope.() -> Unit = {
        Text(
            LocalContext.current.getString(R.string.PayButton),
        )
    },
) {
    if (isVisible) {
        Button(
            modifier = modifier,
            shape = shape,
            onClick = onClick,
            content = content,
        )
    }
}
