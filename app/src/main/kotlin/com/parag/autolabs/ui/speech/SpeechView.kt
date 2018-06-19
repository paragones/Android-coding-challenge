package com.parag.autolabs.ui.speech

interface SpeechView {
    fun displaySpeech(speechResult: List<String>)
    fun displayNoSpeech()
}