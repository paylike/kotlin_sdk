package com.github.paylike.kotlin_sdk.paylikeStyle.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_engine.error.PaylikeEngineError
import com.github.paylike.kotlin_request.exceptions.PaylikeException
import com.github.paylike.kotlin_request.exceptions.RateLimitException
import com.github.paylike.kotlin_request.exceptions.ServerErrorException
import com.github.paylike.kotlin_request.exceptions.VersionException
import com.github.paylike.kotlin_sdk.ErrorCodeToResourceStringMap
import com.github.paylike.kotlin_sdk.R
import com.github.paylike.kotlin_sdk.cardprovider.CardProviderDescriptionMap
import com.github.paylike.kotlin_sdk.cardprovider.CardProviderIconMap
import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders
import com.github.paylike.kotlin_sdk.theme.LocalPaylikePaddings
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 */
@Composable
fun CardProviderIcon(
    modifier: Modifier = Modifier,
    showedCardProviderIcon: SupportedCardProviders,
    isHighlighted: Boolean,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = CardProviderIconMap[showedCardProviderIcon]!!),
        contentDescription =
            stringResource(id = CardProviderDescriptionMap[showedCardProviderIcon]!!),
        colorFilter = if (isHighlighted) null else ColorFilter.tint(Color.Gray),
    )
}

/**
 */
@Composable
fun CardProviderIcons(
    modifier: Modifier = Modifier,
    highlightedCardProvider: SupportedCardProviders
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CardProviderIcon(
            modifier = Modifier.size(48.dp).padding(horizontal = 8.dp),
            showedCardProviderIcon = SupportedCardProviders.MAESTRO,
            isHighlighted = highlightedCardProvider == SupportedCardProviders.MAESTRO,
        )
        CardProviderIcon(
            modifier = Modifier.size(48.dp).padding(horizontal = 8.dp),
            showedCardProviderIcon = SupportedCardProviders.MASTERCARD,
            isHighlighted = highlightedCardProvider == SupportedCardProviders.MASTERCARD,
        )
        CardProviderIcon(
            modifier = Modifier.size(48.dp).padding(horizontal = 8.dp),
            showedCardProviderIcon = SupportedCardProviders.VISA,
            isHighlighted = highlightedCardProvider == SupportedCardProviders.VISA,
        )
    }
}

@Composable
fun SecurePaymentLabel(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.padding(LocalPaylikePaddings.current.smallPadding),
            painter = painterResource(id = R.drawable.ic_paylike),
            contentDescription = stringResource(id = R.string.PAYLIKE_ICON)
        )
        Text(
            modifier = Modifier,
            style = textStyle,
            text = stringResource(id = R.string.POWERED_BY),
        )
    }
}

@Composable
fun LoadingSpinner(
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
) {
    val sweepAngle: Float = 90f
    val strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth
    val transition = rememberInfiniteTransition()
    val currentArcStartAngle by
        transition.animateValue(
            0,
            360,
            Int.VectorConverter,
            infiniteRepeatable(animation = tween(durationMillis = 1100, easing = LinearEasing))
        )
    val stroke =
        with(LocalDensity.current) { Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square) }
    Canvas(modifier.progressSemantics().padding(strokeWidth / 2)) {
        drawArc(
            color,
            startAngle = currentArcStartAngle.toFloat() - 90,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke
        )
        drawArc(
            color,
            startAngle = currentArcStartAngle.toFloat() + 90,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke
        )
    }
}

@Composable
fun SuccessAnimation(
    modifier: Modifier = Modifier,
    delay: Long = 600,
) {
    var visible by remember { mutableStateOf(false) }
    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            delay(delay)
            visible = true
        }
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxSize(1f),
            painter = painterResource(id = R.drawable.bg_successful_payment),
            contentDescription = null,
        )
        Image(
            modifier = Modifier.fillMaxSize(0.8f),
            painter = painterResource(id = R.drawable.ic_successful_payment_circle),
            contentDescription = null
        )
        val density = LocalDensity.current
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically { with(density) { -400.dp.roundToPx() } }
        ) {
            Image(
                modifier = Modifier.fillMaxSize(0.5f).padding(25.dp, 0.dp, 0.dp, 0.dp),
                painter = painterResource(id = R.drawable.ic_successful_payment_checkmark),
                contentDescription = null
            )
        }
    }
}

@Composable
fun ErrorLog(
    modifier: Modifier,
    error: PaylikeEngineError,
) {
    val message =
        if (error.requestException != null) {
            when (error.requestException!!::class) {
                PaylikeException::class -> {
                    stringResource(
                        id =
                            ErrorCodeToResourceStringMap[
                                (error.requestException as PaylikeException).code]!!
                    )
                }
                ServerErrorException::class -> {
                    stringResource(
                        id = R.string.ERROR_UNEXPECTED,
                        (error.requestException as ServerErrorException).message!!
                    )
                }
                VersionException::class -> {
                    stringResource(
                        id = R.string.ERROR_UNEXPECTED,
                        (error.requestException as ServerErrorException).message!!
                    )
                }
                RateLimitException::class -> {
                    stringResource(id = R.string.ERROR_RATE_LIMIT)
                }
                else -> {
                    ""
                }
            }
        } else if (error.clientException != null) {
            error.clientException!!.message
        } else if (error.engineException != null) {
            error.engineException!!.message
        } else if (error.webViewException != null) {
            error.webViewException!!.message
        } else {
            ""
        }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            color = MaterialTheme.colors.error,
        ) {
            Text(
                modifier =
                    Modifier.fillMaxWidth().padding(all = PaylikeTheme.paddings.defaultPadding),
                color = MaterialTheme.colors.onError,
                text = message!!,
            )
        }
    }
}
