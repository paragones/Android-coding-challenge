package com.parag.autolabs.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.LocationManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import com.parag.autolabs.R
import com.parag.autolabs.services.concatenateAlphaAnimations
import com.parag.autolabs.services.gone
import com.parag.autolabs.services.invisible
import com.parag.autolabs.services.visible
import com.parag.autolabs.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_speech.*
import java.util.*
import javax.inject.Inject
import android.location.Geocoder
import com.parag.autolabs.models.WeatherResult


class MainActivity : BaseActivity(), MainView {
    private val RESULT_SPEECH = 10

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(this.javaClass.simpleName, "onCreate")
        setContentView(R.layout.activity_speech)
        component().inject(this)
        setupView()
    }

    private fun setupView() {
        title = getString(R.string.speech_title)
        presenter.attach(this)
        microphone.setOnClickListener {
            if (isPermissionGranted) listen(this)
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

    private fun listen(activity: Activity) {
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

    override fun displaySpeech(weatherResult: List<WeatherResult>) {
        weatherText.text = weatherResult[0].toString()
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
