package com.github.paylike.kotlin_sdk.visualformatter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

/**
 *
 */
class CardVerificationCodeVisualTransformation(
    private val mask: Char = '\u2022',
    private val validColor: Color,
    private val invalidColor: Color,
    private val isValid: Boolean,
): VisualTransformation {
    private val placeholderColor: Color = Color.LightGray
    private val disabledColor: Color = Color.Gray // TODO maybe delete it

    override fun filter(text: AnnotatedString): TransformedText {
        /**
         * Define placeholder
         */
        val placeholder = "***"
        val placeholderLength = placeholder.length

        /**
         * Generate the typed part
         */
        val typedInPart = mask.toString().repeat(text.text.length)

        /**
         * Create [AnnotatedString] so be able to color the text partially
         */
        val typedInPartAnnotatedString = AnnotatedString(
            text = typedInPart,
            spanStyle = SpanStyle(
                color = if (isValid) validColor else invalidColor,
            ),
        )

        /**
         * Trim the unnecessary part of the placeholder then make [AnnotatedString] to be able to color the text partially
         */
        val trimmedPlaceholder = placeholder.substring((0 + text.text.length) until placeholderLength)
        val placeholderAnnotatedString = AnnotatedString(
            text = trimmedPlaceholder,
            spanStyle = SpanStyle(
                color = placeholderColor,
            ),
        )

        /**
         * Concatenate the part then add a [ParagraphStyle] to align them together
         */
        val finalText = typedInPartAnnotatedString + placeholderAnnotatedString
        finalText.paragraphStyles.plus(
            ParagraphStyle(
                textAlign = TextAlign.Center,
            )
        )

        return TransformedText(
            finalText,
            OffsetMapping.Identity
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PasswordVisualTransformation) return false
        if (mask != other.mask) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}
