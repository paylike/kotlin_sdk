package com.github.paylike.sample.viewmodel

import com.github.paylike.kotlin_client.domain.dto.payment.request.money.PaymentAmount
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.PaymentTestDto
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.card.CardCodeOptions
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.card.CardSchemeOptions
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.card.CardStatusOptions
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.card.TestCardDto

val errorCases: Map<String, PaymentTestDto> =
    mapOf(
        "Unsupported card scheme" to
            PaymentTestDto(
                card =
                    TestCardDto(
                        scheme = CardSchemeOptions.UNSUPPORTED,
                    ),
            ),
        "Unknown card scheme" to
            PaymentTestDto(
                card =
                    TestCardDto(
                        scheme = CardSchemeOptions.UNKNOWN,
                    ),
            ),
        "Invalid CVC" to
            PaymentTestDto(
                card =
                    TestCardDto(
                        code = CardCodeOptions.INVALID,
                    ),
            ),
        "Invalid card" to
            PaymentTestDto(
                card =
                    TestCardDto(
                        status = CardStatusOptions.INVALID,
                    ),
            ),
        "Expired card" to
            PaymentTestDto(
                card =
                    TestCardDto(
                        status = CardStatusOptions.EXPIRED,
                    ),
            ),
        "Disabled card" to
            PaymentTestDto(
                card =
                    TestCardDto(
                        status = CardStatusOptions.DISABLED,
                    ),
            ),
        "Lost card" to
            PaymentTestDto(
                card =
                    TestCardDto(
                        status = CardStatusOptions.LOST,
                    ),
            ),
        "Not enough limit" to
            PaymentTestDto(
                card =
                    TestCardDto(
                        limit =
                            PaymentAmount(
                                "EUR",
                                1L,
                                1,
                            ),
                    ),
            ),
        "Insufficient balance" to
            PaymentTestDto(
                card =
                    TestCardDto(
                        balance =
                            PaymentAmount(
                                "EUR",
                                1L,
                                1,
                            ),
                    ),
            ),
    )
