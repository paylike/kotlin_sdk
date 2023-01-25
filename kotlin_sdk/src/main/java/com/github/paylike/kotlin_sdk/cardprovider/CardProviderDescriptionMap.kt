package com.github.paylike.kotlin_sdk.cardprovider

import com.github.paylike.kotlin_sdk.R

/** Mapping [SupportedCardProviders] to find the corresponding resource to load */
val CardProviderDescriptionMap: Map<SupportedCardProviders, Int?> =
    mapOf(
        SupportedCardProviders.NONE to null,
        SupportedCardProviders.MAESTRO to R.string.MAESTRO_ICON,
        SupportedCardProviders.MASTERCARD to R.string.MASTERCARD_ICON,
        SupportedCardProviders.VISA to R.string.VISA_ICON,
    )
