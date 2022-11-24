package com.github.paylike.kotlin_sdk

import com.github.paylike.kotlin_request.exceptions.api.ApiCodes

val ErrorCodeToResourceStringMap: Map<ApiCodes, Int> =
    mapOf(
        /** Internal and network errors */
        ApiCodes.INTERNAL_ERROR to R.string.ERROR_UNEXPECTED,
        ApiCodes.ENDPOINT_NOT_FOUND to R.string.ERROR_UNEXPECTED,

        //    ApiCodes.VERSION_MISSING to R.string.,
        //    ApiCodes.VERSION_UNSUPPORTED to R.string.,

        //    ApiCodes.BODY_INVALID to R.string.,
        //    ApiCodes.REQUEST_INVALID to R.string.,

        //    ApiCodes.TOKEN_INVALID to R.string.,
        //    ApiCodes.TOKEN_TYPE_UNEXPECTED to R.string.,

        /**
         */
        ApiCodes.TEST_MODE_MIXED to
            R.string.ERROR_PUBLIC_KEY_IS_TEST, // TODO not sure about this choice
        ApiCodes.PAYMENT_INTEGRATION_KEY_UNKNOWN to R.string.ERROR_PUBLIC_KEY_UNKNOWN,
        ApiCodes.PAYMENT_INTEGRATION_DISABLED to R.string.ERROR_PUBLIC_KEY_PAYMENTS_NOT_ALLOWED,

        //    ApiCodes.PAYMENT_CHALLENGE_UNAVAILABLE to R.string.,

        /**
         */
        ApiCodes.PAYMENT_CARD_NUMBER_INVALID to R.string.ERROR_INVALID_CARD_NUMBER,
        ApiCodes.PAYMENT_CARD_SCHEME_UNKNOWN to R.string.ERROR_CARD_SCHEME_UNKNOWN,
        ApiCodes.PAYMENT_CARD_SCHEME_UNSUPPORTED to R.string.ERROR_CARD_SCHEME_UNSUPPORTED,
        ApiCodes.PAYMENT_CARD_SECURITY_CODE_INVALID to R.string.ERROR_CARD_SECURITY_CODE_INVALID,
        ApiCodes.PAYMENT_CARD_EXPIRED to R.string.ERROR_CARD_EXPIRED,
        ApiCodes.PAYMENT_CARD_DISABLED to R.string.ERROR_CARD_DISABLED,
        ApiCodes.PAYMENT_CARD_LOST to R.string.ERROR_CARD_LOST,
        ApiCodes.PAYMENT_AMOUNT_LIMIT to R.string.ERROR_AMOUNT_LIMIT,
        ApiCodes.PAYMENT_INSUFFICIENT_FUNDS to R.string.ERROR_INSUFFICIENT_FUNDS,
        ApiCodes.PAYMENT_RECEIVER_BLOCKED to R.string.ERROR_RECEIVER_BLOCKED,
        ApiCodes.PAYMENT_REJECTED_BY_ISSUER to R.string.ERROR_REJECTED_BY_ISSUER,
        ApiCodes.PAYMENT_REJECTED to R.string.ERROR_REJECTED,

        //    ApiCodes.PAYMENT_METHOD_ERROR to R.string.,

        /**
         */
        ApiCodes.TDSECURE_REQUIRED to R.string.ERROR_TDSECURE_REQUIRED,
        ApiCodes.TDSECURE_FAILED to R.string.ERROR_TDSECURE_FAILED,

        //    ApiCodes.TDSECURE_PARES_INVALID to R.string.,
        )
