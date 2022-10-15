package com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel

import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders
import com.github.paylike.kotlin_sdk.CardNumberField
import com.github.paylike.kotlin_sdk.ExpiryDateField
import com.github.paylike.kotlin_sdk.CardVerificationCodeField


/**
 * Contains every UI specific variable state
 */
data class SimplePaymentFormStateModel(
    /**
     * [CardNumberField] depends on these states to function
     */
    val cardNumber: String = "",
    val isCardNumberValid: Boolean = true,
    /**
     * [CardNumberField] depends on this to highlight the relevant icon
     */
    val highlightedCardProvider: SupportedCardProviders = SupportedCardProviders.NONE,

    /**
     * [ExpiryDateField] depends on these states to function
     */
    val expiryDate: String = "",
    val isExpiryDateValid: Boolean = true,

    /**
     * [CardVerificationCodeField] depends on these states to function
     */
    val cardVerificationCode: String = "",
    val isCardVerificationCodeValid: Boolean = true,

    /**
     * Flags to store if given element should be visible or enabled
     *
     * [isFinished] can be successful and with error
     */
    val isInitialState: Boolean = true,
    val isFinished: Boolean = false,
)
