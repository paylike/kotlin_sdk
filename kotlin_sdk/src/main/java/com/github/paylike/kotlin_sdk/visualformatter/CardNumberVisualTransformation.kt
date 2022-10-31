package com.github.paylike.kotlin_sdk.visualformatter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders
import com.github.paylike.kotlin_sdk.cardprovider.calculateProviderFromNumber

/**
 */
class CardNumberVisualTransformation(
    private val validColor: Color,
    private val invalidColor: Color,
    private val isValid: Boolean,
) : VisualTransformation {
    private val placeholderColor: Color = Color.LightGray

    override fun filter(text: AnnotatedString): TransformedText {
        /** Calculate possible actual card provider */
        val isMaestro = calculateProviderFromNumber(text.text) == SupportedCardProviders.MAESTRO

        /** Define placeholder */
        var placeholder = "#### #### #### #### "
        if (isMaestro) {
            placeholder += "###"
        }
        val placeholderLength = placeholder.length

        /** Generate the padded typed part */
        var typedInPart = ""
        for (i in text.indices) {
            typedInPart += text[i]
            if (i % 4 == 3) typedInPart += " "
        }
        val lengthOfTypedInPart = typedInPart.length

        /** Create [AnnotatedString] so be able to color the text partially */
        val typedInPartAnnotatedString =
            AnnotatedString(
                text = typedInPart,
                spanStyle =
                    SpanStyle(
                        color = if (isValid) validColor else invalidColor,
                    ),
            )

        /**
         * Trim the unnecessary part of the placeholder then make [AnnotatedString] to be able to
         * color the text partially
         */
        val trimmedPlaceholder =
            placeholder.substring((0 + lengthOfTypedInPart) until placeholderLength)
        val placeholderAnnotatedString =
            AnnotatedString(
                text = trimmedPlaceholder,
                spanStyle =
                    SpanStyle(
                        color = placeholderColor,
                    ),
            )

        /** Concatenate the part then add a [ParagraphStyle] to align them together */
        val finalText = typedInPartAnnotatedString + placeholderAnnotatedString
        finalText.paragraphStyles.plus(
            ParagraphStyle(
                textAlign = TextAlign.Center,
            )
        )

        /**
         */
        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return if (offset <= 3) offset
                    else if (offset <= 7) offset + 1
                    else if (offset <= 11) offset + 2
                    else if (offset <= 16) offset + 3
                    else if (offset <= 19) offset + 4 else placeholderLength
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return if (offset <= 4) offset
                    else if (offset <= 9) offset - 1
                    else if (offset <= 14) offset - 2
                    else if (offset <= 19) offset - 3
                    else if (offset <= 23) offset - 4 else if (isMaestro) 19 else 16
                }
            }

        /**
         */
        return TransformedText(finalText, offsetMapping)
    }
}
