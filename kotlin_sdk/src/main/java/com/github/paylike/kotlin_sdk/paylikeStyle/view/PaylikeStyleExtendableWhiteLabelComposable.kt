package com.github.paylike.kotlin_sdk.paylikeStyle.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.whitelabel.extendable.view.ExtendableWhiteLabelComposable
import com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel.ExtendableWhiteLabelViewModel

/** Whole coherent UI composition */
@Composable
fun PaylikeStyleExtendableWhiteLabelComposable(
    modifier: Modifier = Modifier,
    viewModel: ExtendableWhiteLabelViewModel,
) {
    ExtendableWhiteLabelComposable(
        viewModel = viewModel,
        theme = { content -> run { PaylikeTheme() { content.invoke() } } },
    )
}
