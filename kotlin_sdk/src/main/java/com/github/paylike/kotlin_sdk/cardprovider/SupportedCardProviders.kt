package com.github.paylike.kotlin_sdk.cardprovider

/**
 * With a Paylike account, you can accept Visa, Mastercard and Maestro cards as well as any
 * sub-brand hereof (Electron, Visa-Dankort, debit, credit, business, etc.). Mastercard ID Check and
 * Visa Secure are enabled by default free of charge.
 *
 * IIN ranges
 *
 * Visa starts with 4. Pan length is 16. MasterCard starts with numbers between 22 and 27, between
 * 51 and 55. Pan length is 16. Maestro starts with numbers between 50, 56-69. Pan varies between
 * 16-19.
 *
 * Furthermore: American Express starts with 34 or 37. Pan length is 15. Japan Credit Bureau starts
 * with 35. Diner's Club starts with 36 or 38 Discover starts with 6011 or 65.
 *
 * @see <a href="https://www.bindb.com/card-schemes">Source</a>
 */
enum class SupportedCardProviders {
    NONE,
    MAESTRO,
    MASTERCARD,
    VISA,
}
