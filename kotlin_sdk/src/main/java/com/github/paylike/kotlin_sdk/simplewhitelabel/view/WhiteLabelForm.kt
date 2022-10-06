package com.github.paylike.kotlin_sdk.simplewhitelabel.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.PaylikeGreen
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.view.*
import com.github.paylike.kotlin_sdk.viewmodel.BasicViewModel

@Composable
fun WhiteLabelFormComposable(viewModel: BasicViewModel) {
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
