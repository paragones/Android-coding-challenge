package com.parag.autolabs.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.parag.autolabs.R
import com.parag.autolabs.models.WeatherResult
import com.parag.autolabs.services.ImageLoader
import com.parag.autolabs.services.gone
import com.parag.autolabs.services.invisible
import com.parag.autolabs.services.visible
import com.parag.autolabs.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_speech.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class MainActivity : BaseActivity(), MainView {
    private val RESULT_SPEECH = 10
    private var items: ArrayList<WeatherResult> = ArrayList()
    private lateinit var mainAdapter: MainAdapter

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech)
        component().inject(this)
        setupView()
        setupList()
    }

    private fun setupView() {
        title = getString(R.string.speech_title)
        presenter.attach(this)
        microphone.setOnClickListener {
            if (isPermissionGranted) listen(this)
        }
    }

    private fun setupList() {
        mainAdapter = MainAdapter(imageLoader, items, this)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = mainAdapter
    }

    private fun displayLoading() {
        progressBar.visible()
        microphone.invisible()
        weatherText.invisible()
        resultTitle.invisible()
        mainRecyclerView.invisible()
    }

    private fun hideLoading() {
        progressBar.gone()
        microphone.visible()
    }

    private fun listen(activity: Activity) {
        items.clear()
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        activity.startActivityForResult(intent, RESULT_SPEECH)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            if (resultCode == RESULT_OK && requestCode == RESULT_SPEECH && it.hasExtra(RecognizerIntent.EXTRA_RESULTS)) {
                presenter.loadWeather(it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS))
                displayLoading()
            } else {
                Log.e(this.javaClass.simpleName, "Error on getting results")
            }
        }
    }

    override fun displaySpeech(weatherResult: List<WeatherResult>) {
        hideLoading()
        YoYo.with(Techniques.FadeIn)
                .duration(700)
                .playOn(mainRecyclerView)
        items.addAll(weatherResult)
        mainAdapter.notifyDataSetChanged()
        mainRecyclerView.visible()
        weatherText.visible()
        resultTitle.visible()
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
