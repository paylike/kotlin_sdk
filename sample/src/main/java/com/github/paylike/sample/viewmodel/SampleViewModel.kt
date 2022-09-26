package com.github.paylike.sample.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SampleViewModel : ViewModel() {
    var uiState by mutableStateOf(UIState("dummyStringContent"))
        private set
}
