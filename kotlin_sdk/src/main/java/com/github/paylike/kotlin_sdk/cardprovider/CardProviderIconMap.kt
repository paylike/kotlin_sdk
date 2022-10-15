package com.github.paylike.kotlin_sdk.cardprovider

import com.github.paylike.kotlin_sdk.R

/**
 * Mapping [SupportedCardProviders] to find the corresponding resource to load
 */
val CardProviderIconMap: Map<SupportedCardProviders, Int?> =
    mapOf(
        SupportedCardProviders.NONE to null,
        SupportedCardProviders.MAESTRO to R.drawable.ic_maestro_icon,
        SupportedCardProviders.MASTERCARD to R.drawable.ic_mastercard_icon,
        SupportedCardProviders.VISA to R.drawable.ic_visa_icon,
    )
