package com.github.paylike.sample.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.sample.R
import com.github.paylike.sample.viewmodel.SampleViewModel
import com.github.paylike.sample.viewmodel.SdkExampleModel

/** Example application single activity */
class SampleActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Disables the default action bar and default padding values */
        actionBar?.hide()
        WindowCompat.setDecorFitsSystemWindows(window, true)

        /** Sample VM to manage sample application states and predefined example data */
        val model: SampleViewModel by viewModels()

        /** Renders the example application */
        setContent {
            SampleAppComposable(
                model,
            )
        }
    }
}

/** Wrapper composable responsible for scaffold and navigation hosting */
@ExperimentalMaterialApi
@Composable
fun SampleAppComposable(
    viewModel: SampleViewModel,
) {
    val scaffoldState by remember { mutableStateOf(viewModel.scaffoldState) }

    MaterialTheme {
        Surface(modifier = Modifier.systemBarsPadding()) {
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { TopBarContentComposable() },
                content = { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = viewModel.rootRoute,
                    ) {
                        composable(viewModel.rootRoute) {
                            /** Root composable */
                            ExampleListComposable(
                                viewModel,
                                navController,
                            )
                        }
                        viewModel.sdkExampleModelMap.forEach { (keyAsRoute, model) ->
                            composable(keyAsRoute) {
                                Scaffold(
                                    /** To show the title of the example */
                                    topBar = {
                                        Text(
                                            modifier =
                                                Modifier.fillMaxWidth()
                                                    .padding(PaylikeTheme.paddings.smallPadding),
                                            text = LocalContext.current.getString(model.titleId),
                                            style = PaylikeTheme.typography.h6,
                                            textAlign = TextAlign.Center,
                                        )
                                    },
                                ) { padding ->
                                    /** Form composable */
                                    Column(
                                        modifier =
                                            Modifier.fillMaxSize()
                                                .verticalScroll(rememberScrollState(), true)
                                                .imePadding()
                                                .padding(padding),
                                        verticalArrangement = Arrangement.Center,
                                    ) {
                                        model.exampleComposable.invoke(
                                            model.exampleViewModel,
                                            model.paymentData
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                scaffoldState = scaffoldState,
            )
        }
    }
}

@Composable
fun TopBarContentComposable() {
    TopAppBar(
        backgroundColor = PaylikeTheme.colors.primary,
        contentColor = PaylikeTheme.colors.onPrimary,
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = {
                    Text(
                        text = LocalContext.current.getString(R.string.top_app_bar_title),
                        style = PaylikeTheme.typography.h5,
                        textAlign = TextAlign.Center,
                    )
                },
            )
        },
    )
}

/** Renders one card for each defined example case in the [SampleViewModel] */
@ExperimentalMaterialApi
@Composable
private fun ExampleListComposable(viewModel: SampleViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState(), true),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        viewModel.sdkExampleModelMap.forEach { (keyAsRoute, model) ->
            ExampleCard(
                route = keyAsRoute,
                exampleModel = model,
                onClick = { viewModel.toggleCard(key = keyAsRoute) },
                navController = navController,
            )
        }
        /** Language picker located at the end of the example list */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            LocalePicker()
        }
    }
}

/**
 * Holds one example each
 *
 * Displays basic information about the example and has a button to navigate to the example usage.
 */
@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@Composable
fun ExampleCard(
    route: String,
    exampleModel: SdkExampleModel,
    onClick: () -> Unit,
    navController: NavHostController
) {
    /** Local UI states to help animations */
    val uiState = exampleModel.uiStates
    val timeToOpen = 500
    val isUnfolded by remember { mutableStateOf(uiState.isOpen) }
    val angle by remember { mutableStateOf(uiState.iconRotation) }
    val rotate by
        animateFloatAsState(
            targetValue = angle.value,
            tween(timeToOpen),
        )
    /** Holds the [SdkExampleModel] and formats it to the user */
    Card(
        modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 0.dp),
        backgroundColor = PaylikeTheme.colors.secondary,
        onClick = { onClick.invoke() },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    modifier =
                        Modifier.height(50.dp)
                            .fillMaxWidth()
                            .background(PaylikeTheme.colors.secondaryVariant),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.padding(PaylikeTheme.paddings.smallPadding),
                        text = LocalContext.current.getString(exampleModel.titleId),
                        style = PaylikeTheme.typography.button
                    )
                    Icon(
                        imageVector = Icons.Rounded.ArrowDropDown,
                        modifier =
                            Modifier.padding(PaylikeTheme.paddings.smallPadding).rotate(rotate),
                        contentDescription = null,
                    )
                }
                AnimatedContent(
                    modifier = Modifier.fillMaxWidth().offset(),
                    contentAlignment = Alignment.TopStart,
                    targetState = isUnfolded.value,
                    transitionSpec = {
                        expandVertically(animationSpec = tween(500)) with
                            shrinkVertically(animationSpec = tween(500)) /*using*/
                        fadeIn(animationSpec = tween(500, easing = LinearEasing)) with
                            fadeOut(animationSpec = tween(500, easing = LinearEasing)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        IntSize(targetSize.width, initialSize.height) at 600
                                        PaddingValues(0.dp)
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        IntSize(initialSize.width, targetSize.height) at 600
                                        PaddingValues(0.dp)
                                        durationMillis = 300
                                    }
                                }
                            }
                    }
                ) { targetExpanded ->
                    if (targetExpanded) {
                        Column(
                            modifier =
                                Modifier.fillMaxSize().padding(PaylikeTheme.paddings.smallPadding),
                            Arrangement.Top,
                            Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = LocalContext.current.getString(exampleModel.descriptionId),
                                style = PaylikeTheme.typography.body1
                            )
                            Button(
                                onClick = {
                                    navController.navigate(
                                        route = route,
                                    )
                                },
                            ) {
                                Text(
                                    text =
                                        LocalContext.current.getString(
                                            exampleModel.exampleButtonTextId
                                        ),
                                    style = PaylikeTheme.typography.button,
                                )
                            }
                        }
                    }
                }
            }
        },
    )
}
