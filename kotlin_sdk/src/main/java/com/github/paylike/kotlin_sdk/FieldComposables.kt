package com.github.paylike.kotlin_sdk

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders
import com.github.paylike.kotlin_sdk.paylikeStyle.view.CardProviderIcons
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.visualformatter.CardNumberVisualTransformation
import com.github.paylike.kotlin_sdk.visualformatter.CardVerificationCodeVisualTransformation
import com.github.paylike.kotlin_sdk.visualformatter.ExpirationDateVisualTransformation

@Composable
fun CardNumberField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isValid: Boolean,
    isEnabled: Boolean = true,
    onValueChanged: (String) -> Unit,
    highlightedCardProvider: SupportedCardProviders,
    focusManager: FocusManager,
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
        visualTransformation =
            CardNumberVisualTransformation(
                validColor = PaylikeTheme.colors.onBackground,
                invalidColor = PaylikeTheme.colors.error,
                isValid = isValid,
            ),
        singleLine = true,
        enabled = isEnabled,
        isError = !isValid,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next,
            ),
        colors =
            TextFieldDefaults.textFieldColors(
                backgroundColor = PaylikeTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        keyboardActions =
            KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) },
            ),
    )
}

@Composable
fun ExpiryDateField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isValid: Boolean,
    isEnabled: Boolean = true,
    onValueChanged: (String) -> Unit,
    focusManager: FocusManager,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        visualTransformation =
            ExpirationDateVisualTransformation(
                validColor = PaylikeTheme.colors.onBackground,
                invalidColor = PaylikeTheme.colors.error,
                isValid = isValid,
            ),
        singleLine = true,
        enabled = isEnabled,
        isError = !isValid,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next,
            ),
        colors =
            TextFieldDefaults.textFieldColors(
                backgroundColor = PaylikeTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
    )
}

@Composable
fun CardVerificationCodeField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isValid: Boolean,
    isEnabled: Boolean = true,
    onValueChanged: (String) -> Unit,
    focusManager: FocusManager,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        visualTransformation =
            CardVerificationCodeVisualTransformation(
                validColor = PaylikeTheme.colors.onBackground,
                invalidColor = PaylikeTheme.colors.error,
                isValid = isValid,
            ),
        enabled = isEnabled,
        isError = !isValid,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done,
            ),
        colors =
            TextFieldDefaults.textFieldColors(
                backgroundColor = PaylikeTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
    )
}

@Composable
fun NoteField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    isEnabled: Boolean = true,
    onValueChanged: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

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
        enabled = isEnabled,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
        colors =
            TextFieldDefaults.textFieldColors(
                backgroundColor = PaylikeTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
    )
}

@Composable
fun PayButton(
    modifier: Modifier = Modifier,
    shape: Shape = PaylikeTheme.shapes.medium,
    onClick: () -> Unit,
    isVisible: Boolean = true,
    focusManager: FocusManager,
    content: @Composable RowScope.() -> Unit = {
        Text(
            LocalContext.current.getString(R.string.PAY),
        )
    },
) {
    if (isVisible) {
        Button(
            modifier = modifier,
            shape = shape,
            onClick = {
                focusManager.clearFocus()
                onClick()
            },
            content = content,
        )
    }
}
