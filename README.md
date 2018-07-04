<img src="/assets/whatstheweather.gif">

# Autolabs Code Challenge

An Android Application for German Autolabs.

This project aims to show a list of simple weather reports.
It is written in Kotlin.

### How it works

Just press the microphone button and ask for the weather.

- If you didn't say the word 'weather', it will not return any results
- If you say the word weather but didn't specify the city, it will return the result of your current city
- If you say the word weather and with a corresponding city, it will return a list of cities that is close to what you said.

### How to run it

Run the app by (after downloading)

- executing `gradle assembleRelese` on adb or terminal
- press the play button on Android Studio if ever

<i>Note: since the app uses Openweathermap Api, in order to build the app, it needs to get an Api Key on
https://openweathermap.org/appid. Afterwards, save it on gradle.properties or local.properties </i>

### Code Design and Architectural Solution

The framework made for this challenge was initially made with the MVP pattern in mind. It also abides in CLEAN Architecture with SOLID principles (To meet up the universal rules requirements).
Unit testing were also created for checking business and logic rules.

<b> Task description Requirements: </b>

    Uses MVP together with CLEAN persistence layers
    Uses Material Design patterns principles for modern designs
    Uses Recycler View, Constraint Layout, Picasso Image Loader for best UI performance
    Best Libraries used: Dagger, Retrofit2, RxJava2, Okhttp, and Kotlin
    Although not a library itself, I've added some extension classes (AnimationExtension and UiUtils). Its some of the collaboration of my work together my previous colleagues to shorten layout creation.

Libraries Used:

	Kotlin
	Retrofit
	Dagger 2
	RxJava
	Gson
	Rounded Image View
	Picasso Image Loader
	SpinKit

Classes Used:

Config:	- handles the class dependencies and main application class.
	MainApplication

	Dagger Components:
		Activities Component

	Dagger Modules:
		ThreadModule
		RepositoryModule
		ServiceModule


Models : - handles all the stored objects to used all over the app

	WeatherResults - the Weather response object

Repositories : - handles the data source of the CLEAN layer.

	WeatherRepository - repository interface
	WeatherRepositoryImpl - WeatherRepository implementation. Managers rest endpoint calls.

Rest : - part of the data source where CLEAN layer handles the classes needed for the REST Api.

	WeatherRest - holds the response object from REST Api.

Schedulers :- handles thread Scheduling for the REST calls.

	ThreadScheduler - interface scheduler
	ThreadSchedulerImpl - thread class used for handling RxJava observation and subscription thread.

Services :- handles the services to be used for the app. Such as the database

	DataMapperImpl - Mapping service from response to business object
    DateUtil - handles date services
    DefaultParcelable - handles objects serialization and parcelization
    PicassoImageLoader - handles network image viewing

UI :- ui class that handles the views for the app

	MainActivity - activity class used for viewing the responses and behavior of the app
	MainAdapter - pager adapter class to handle card views that would be needed to show different repositories
	MainPresenter - handles the decison making on which view will be seen on the activity
	MainView - a callback interface that is used by the presenter to show which views will be used

Unit Testing :- Automated unit test

    MainPresenterTest - tests if the presenter correctly shows the right view on certain conditions

Improvements

    Given more time, I would have made more changes in the following:
    - improve more on the UI especially on how to show results
    - put more tests
    - creating a CI to test and deploy

