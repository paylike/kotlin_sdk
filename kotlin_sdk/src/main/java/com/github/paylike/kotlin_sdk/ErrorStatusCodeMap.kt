package com.github.paylike.kotlin_sdk

import com.github.paylike.kotlin_request.exceptions.apistatuscodes.ApiCodesEnum

/**
 */
val ErrorCodeToResourceStringMap: Map<ApiCodesEnum, Int> =
    mapOf(
        /** Internal and network errors */
        ApiCodesEnum.INTERNAL_ERROR to R.string.ERROR_UNEXPECTED,
        ApiCodesEnum.ENDPOINT_NOT_FOUND to R.string.ERROR_UNEXPECTED,

        //    ApiCodesEnum.VERSION_MISSING to R.string.,
        //    ApiCodesEnum.VERSION_UNSUPPORTED to R.string.,

        //    ApiCodesEnum.BODY_INVALID to R.string.,
        //    ApiCodesEnum.REQUEST_INVALID to R.string.,

        //    ApiCodesEnum.TOKEN_INVALID to R.string.,
        //    ApiCodesEnum.TOKEN_TYPE_UNEXPECTED to R.string.,

        /**
         */
        ApiCodesEnum.TEST_MODE_MIXED to
            R.string.ERROR_PUBLIC_KEY_IS_TEST, // TODO not sure about this choice
        ApiCodesEnum.PAYMENT_INTEGRATION_KEY_UNKNOWN to R.string.ERROR_PUBLIC_KEY_UNKNOWN,
        ApiCodesEnum.PAYMENT_INTEGRATION_DISABLED to R.string.ERROR_PUBLIC_KEY_PAYMENTS_NOT_ALLOWED,

        //    ApiCodesEnum.PAYMENT_CHALLENGE_UNAVAILABLE to R.string.,

        /**
         */
        ApiCodesEnum.PAYMENT_CARD_NUMBER_INVALID to R.string.ERROR_INVALID_CARD_NUMBER,
        ApiCodesEnum.PAYMENT_CARD_SCHEME_UNKNOWN to R.string.ERROR_CARD_SCHEME_UNKNOWN,
        ApiCodesEnum.PAYMENT_CARD_SCHEME_UNSUPPORTED to R.string.ERROR_CARD_SCHEME_UNSUPPORTED,
        ApiCodesEnum.PAYMENT_CARD_SECURITY_CODE_INVALID to
            R.string.ERROR_CARD_SECURITY_CODE_INVALID,
        ApiCodesEnum.PAYMENT_CARD_EXPIRED to R.string.ERROR_CARD_EXPIRED,
        ApiCodesEnum.PAYMENT_CARD_DISABLED to R.string.ERROR_CARD_DISABLED,
        ApiCodesEnum.PAYMENT_CARD_LOST to R.string.ERROR_CARD_LOST,
        ApiCodesEnum.PAYMENT_AMOUNT_LIMIT to R.string.ERROR_AMOUNT_LIMIT,
        ApiCodesEnum.PAYMENT_INSUFFICIENT_FUNDS to R.string.ERROR_INSUFFICIENT_FUNDS,
        ApiCodesEnum.PAYMENT_RECEIVER_BLOCKED to R.string.ERROR_RECEIVER_BLOCKED,
        ApiCodesEnum.PAYMENT_REJECTED_BY_ISSUER to R.string.ERROR_REJECTED_BY_ISSUER,
        ApiCodesEnum.PAYMENT_REJECTED to R.string.ERROR_REJECTED,

        //    ApiCodesEnum.PAYMENT_METHOD_ERROR to R.string.,

        /**
         */
        ApiCodesEnum.TDSECURE_REQUIRED to R.string.ERROR_TDSECURE_REQUIRED,
        ApiCodesEnum.TDSECURE_FAILED to R.string.ERROR_TDSECURE_FAILED,

        //    ApiCodesEnum.TDSECURE_PARES_INVALID to R.string.,
        )
