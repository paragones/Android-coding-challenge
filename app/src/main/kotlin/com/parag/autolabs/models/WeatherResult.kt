package com.parag.autolabs.models

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import com.parag.autolabs.services.DefaultParcelable
import com.parag.autolabs.services.read
import com.parag.autolabs.services.write
import java.io.Serializable
import java.util.*

data class WeatherResult(@SerializedName("name")val name: String,
                         @SerializedName("weather") val weatherJson: List<WeatherJson>,
                         @SerializedName("main") val weatherMain: WeatherMainJson,
                         @SerializedName("sys") val weatherSystem: WeatherSystemJson): DefaultParcelable, Serializable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(name,
                weatherJson,
                weatherMain)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            WeatherResult(it.read(), it.read(), it.read(), it.read())
        }
    }
}

data class WeatherJson(@SerializedName("main")val main:String,
                       @SerializedName("description")val description: String,
                       @SerializedName("icon") val icon: String): DefaultParcelable, Serializable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(main, description, icon)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            WeatherJson(it.read(), it.read(), it.read())
        }
    }
}

data class WeatherMainJson(@SerializedName("temp")val temp: String): DefaultParcelable, Serializable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(temp)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            WeatherMainJson(it.read())
        }
    }
}

data class WeatherSystemJson(@SerializedName("sunrise") val sunrise: Long,
                             @SerializedName("sunset") val sunset: Long): DefaultParcelable, Serializable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(sunrise, sunset)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            WeatherSystemJson(it.read(), it.read())
        }
    }
}
