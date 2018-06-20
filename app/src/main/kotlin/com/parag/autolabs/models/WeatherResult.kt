package com.parag.autolabs.models

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import com.parag.autolabs.services.DefaultParcelable
import com.parag.autolabs.services.read
import com.parag.autolabs.services.write
import java.io.Serializable

data class WeatherResult(@SerializedName("name")val name: String,
                         @SerializedName("weather") private val weatherJson: List<WeatherJson>,
                         @SerializedName("main") private val weatherMain: MainJson): DefaultParcelable, Serializable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(name,
                weatherJson,
                weatherMain)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            WeatherResult(it.read(), it.read(), it.read())
        }
    }
}

data class WeatherJson(@SerializedName("main")val main:String): DefaultParcelable, Serializable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(main)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            WeatherJson(it.read())
        }
    }
}

data class MainJson(@SerializedName("temp")val temp: String): DefaultParcelable, Serializable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(temp)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            MainJson(it.read())
        }
    }
}
