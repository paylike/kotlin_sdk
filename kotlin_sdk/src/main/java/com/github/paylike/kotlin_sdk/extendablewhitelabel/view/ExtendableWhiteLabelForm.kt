package com.github.paylike.kotlin_sdk.extendablewhitelabel.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.paylike.kotlin_sdk.extendablewhitelabel.viewmodel.ExtendableWhiteLabelViewModel
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.PaylikeErrorRed
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.PaylikeGreen
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.view.*

@Composable
fun ExtendableWhiteLabelFormComposable(viewModel: ExtendableWhiteLabelViewModel) {
    val uiState = viewModel.BasicUIState

    PaylikeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("First name",
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 15.dp)
                    )
                    Text("Last name",
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    FirstNameInput(
                        viewModel.FirstName,
                        viewModel.isFirstNameInputValid,
                        { viewModel.handleFirstNameInputChange(it) },
                        Modifier.weight(1f)
                    )
                    LastNameInput(
                        viewModel.LastName,
                        viewModel.isLastNameInputValid,
                        { viewModel.handleLastNameInputChange(it) },
                        Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    CardNumber(
                        uiState.cardNumber,
                        uiState.isCardNumberValid,
                        { viewModel.handleCardInputChange(it) },
                        Modifier.weight(55f)
                    )
                    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.weight(45f)) {
                        MasterCardImage(uiState.highlightedCardBrand)
                        VisaImage(uiState.highlightedCardBrand)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Expiration(
                        uiState.expiryDate,
                        uiState.isExpiryDateValid,
                        { viewModel.handleExpirationInputChange(it) },
                        Modifier.weight(65f)
                    )
                    SecurityCode(
                        uiState.securityCode,
                        uiState.isSecurityCodeValid,
                        { viewModel.handleSecurityCodeChange(it) },
                        Modifier.weight(35f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    NoteInput(
                        viewModel.Note,
                        onValueChanged = { viewModel.handleNoteInputChange(it) }
                    )
                }
                Button(
                    colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = PaylikeGreen,
                        contentColor = Color.White
                    ),
                    onClick = { viewModel.handleButtonClick() },
                    modifier = Modifier.size(144.dp, 48.dp),
                    shape = RoundedCornerShape(12)
                ) {
                    Text(
                        "Pay",
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }
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
