package com.github.paylike.kotlin_sdk.whitelabel.extendable.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel.ExtendableWhiteLabelViewModel
import com.github.paylike.kotlin_sdk.theme.PaylikeGreen
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.view.*

@Composable
fun ExtendableWhiteLabelComposable(
    viewModel: ExtendableWhiteLabelViewModel,
    scaffoldState: ScaffoldState,
    extendingContent: List<@Composable () -> Unit> = listOf(),
) {
    val uiState = viewModel.paymentFormState



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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 0.dp),
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
                        CardProviderImage(highlightedCardBrand = uiState.highlightedCardBrand)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
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
