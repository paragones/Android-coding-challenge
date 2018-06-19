package com.parag.autolabs.ui.speech

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import com.parag.autolabs.R
import com.parag.autolabs.R.id.weatherText
import com.parag.autolabs.services.concatenateAlphaAnimations
import com.parag.autolabs.services.gone
import com.parag.autolabs.services.invisible
import com.parag.autolabs.services.visible
import com.parag.autolabs.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_speech.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class SpeechActivity : BaseActivity(), SpeechView {
    val RESULT_SPEECH = 10

    @Inject
    lateinit var presenter: SpeechPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech)
        component().inject(this)
        setupView()
    }

    private fun setupView() {
        title = getString(R.string.speech_title)
        concatenateAlphaAnimations(mutableListOf(weatherText, microphone), 500, 1f)
        presenter.attach(this)
        microphone.setOnClickListener {
//            displayLoading()
            listen(this)
        }
    }

    private fun displayLoading() {
        progressBar.visible()
        microphone.invisible()
    }

    private fun hideLoading() {
        progressBar.gone()
        microphone.visible()
    }

    fun listen(activity: Activity) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        activity.startActivityForResult(intent, RESULT_SPEECH)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            if (resultCode == RESULT_OK && requestCode == RESULT_SPEECH && it.hasExtra(RecognizerIntent.EXTRA_RESULTS))
                presenter.loadWeather(it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS))
            else
                Log.e(this.javaClass.simpleName, "Error on getting results")
        }
    }

    override fun displaySpeech(speechResult: List<String>) {
        weatherText.text = speechResult[0]
        hideLoading()
    }

    override fun displayNoSpeech() {
        weatherText.text = getString(R.string.no_question)
        microphone.visible()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
