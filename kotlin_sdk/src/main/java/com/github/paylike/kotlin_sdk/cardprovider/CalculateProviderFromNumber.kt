package com.github.paylike.kotlin_sdk.cardprovider

import java.util.regex.Pattern

/**
 * Paylike ecosystem only accepts the following cards defined in [SupportedCardProviders] enum class so we only check these.
 */
fun calculateProviderFromNumber(number: String): SupportedCardProviders {
    if (Pattern.compile("^(5[1-5]|222[1-9]|22[3-9]|2[3-6]|27[01]|2720)[0-9]*$").matcher(number).matches()) return SupportedCardProviders.MASTERCARD
    if (Pattern.compile("^(5[06789]|6)[0-9]*\$").matcher(number).matches()) return SupportedCardProviders.MAESTRO
    if (Pattern.compile("^4[0-9]*$").matcher(number).matches()) return SupportedCardProviders.VISA
    return SupportedCardProviders.NONE
}
