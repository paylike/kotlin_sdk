package com.github.paylike.kotlin_sdk.whitelabel.extendable.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_engine.viewmodel.EngineState
import com.github.paylike.kotlin_sdk.*
import com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel.ExtendableWhiteLabelViewModel
import com.github.paylike.kotlin_sdk.theme.PaylikeGreen
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.whitelabel.simple.view.SimpleWhiteLabelFormComposable
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel

@Composable
fun ExtendableWhiteLabelComposable(
    viewModel: ExtendableWhiteLabelViewModel,
    // TODO("make it theme parametrised")
    extendingContent: List<@Composable (ExtendableWhiteLabelViewModel) -> Unit> = listOf(),
) {
    /**
     * Necessary webView to assist the TDS flow.
     * It is responsible to show the catch hints,
     * show challenge,
     * and send challenge response.
     *
     * Contains the logic if it needs to be shown.
     */
    val webView = remember { mutableStateOf(viewModel.webView) }

    /**
     * Debug purposes, shows message that the flow has come to a final state
     *
     * [EngineState.SUCCESS] or [EngineState.SUCCESS]
     */
    if (viewModel.paymentFormState.isFinished) {
        Toast
            .makeText(
                LocalContext.current,
                "Payment flow has finished.",
                Toast.LENGTH_SHORT
            )
            .show()
    }

    /**
     * Wrapped in predefined theme
     */
    PaylikeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            color = MaterialTheme.colors.background
        ) {
            /**
             * Wraps every field
             */
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                /**
                 * WebView to help TDS flow
                 */
                webView.value.WebViewComposable(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(200.dp)
                )

                /**
                 * Previous
                 */
//                extendingContent[0](viewModel)

                /**
                 * Form that contains the fields and the pay button
                 */
                SimpleWhiteLabelFormComposable(
                    modifier = Modifier
                        .fillMaxWidth(),
                    viewModel = viewModel,
                )

                /**
                 * Latter
                 */
//                extendingContent[2](viewModel)


                /**
                 * Pay button
                 */
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    PayButton(
                        modifier = Modifier,
                        onClick = { viewModel.onPayButtonClick() },
                        isVisible = viewModel.paymentFormState.isInitialState,
                    )
                }
            }
        }
    }
}

@Composable
fun ExtendedWhiteLabelFormExampleComposable(
    modifier: Modifier = Modifier,
    viewModel: ViewModel,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text("First name",
                Modifier
                    .weight(1f)
                    .padding(horizontal = 15.dp)
            )
            Text("Last name",
                Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )
        }
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
//            FirstNameField(
//
//            )
//            LastNameField(
//
//            )
        }



        SimpleWhiteLabelFormComposable(
            viewModel =  viewModel as WhiteLabelViewModel,
        )



        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
//            NoteField(
//                onValueChanged = { viewModel.handleNoteInputChange(it) }
//            )
        }
    }
}
