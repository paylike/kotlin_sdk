package com.github.paylike.kotlin_sdk.visualformatter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

class ExpirationDateVisualTransformation(
    private val validColor: Color,
    private val invalidColor: Color,
    private val isValid: Boolean,
) : VisualTransformation {
    private val placeholderColor: Color = Color.LightGray

    override fun filter(text: AnnotatedString): TransformedText {
        /** Define placeholder */
        val placeholder = "MM/YY"
        val placeholderLength = placeholder.length

        /** Generate the padded typed part */
        var typedInPart = ""
        for (i in text.indices) {
            typedInPart += text[i]
            if (i == 1) typedInPart += "/"
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

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 1) return offset
                    if (offset <= 4) return offset + 1
                    return 5
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 2) return offset
                    if (offset <= 5) return offset - 1
                    return 4
                }
            }

        return TransformedText(finalText, offsetMapping)
    }
}
