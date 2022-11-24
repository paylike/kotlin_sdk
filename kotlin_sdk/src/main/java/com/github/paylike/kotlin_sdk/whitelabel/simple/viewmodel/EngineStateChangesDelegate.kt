package com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel

/** Defines the engine state changes callback functions */
interface EngineStateChangesDelegate {
    fun onWaitingForInput(arg: Any? = null) {}
    fun onWebViewChallengeStarted(arg: Any? = null) {}
    fun onWebViewChallengeUserInputRequired(arg: Any? = null) {}
    fun onSuccess(arg: Any? = null) {}
    fun onError(arg: Any? = null) {}
}
