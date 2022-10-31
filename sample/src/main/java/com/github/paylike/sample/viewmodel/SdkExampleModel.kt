package com.github.paylike.sample.viewmodel

import androidx.compose.runtime.Composable
import com.github.paylike.kotlin_client.domain.dto.payment.request.PaymentData
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel

/** Data class to store every data for any Paylike's kotlin SDK example usage */
data class SdkExampleModel(
    val uiStates: CardStates,
    val titleId: Int,
    val descriptionId: Int,
    val exampleButtonTextId: Int,
    val paymentData: PaymentData,
    val exampleViewModel: WhiteLabelViewModel,
    val exampleComposable:
        @Composable
        (exampleViewModel: WhiteLabelViewModel, paymentData: PaymentData) -> Unit,
)
