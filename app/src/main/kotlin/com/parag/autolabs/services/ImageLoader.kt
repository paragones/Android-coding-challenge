package com.parag.autolabs.services

import android.net.Uri
import android.widget.ImageView

interface ImageLoader {
    fun loadInto(uri: Uri?, view: ImageView)
}
