package com.parag.autolabs.ui.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.parag.autolabs.component.ActivityComponent
import com.parag.autolabs.component.DaggerActivityComponent
import com.parag.autolabs.modules.*

abstract class BaseActivity : AppCompatActivity() {
    val PERMISSIONS_REQUEST_ACCESS_LOCATION = 1
    var isPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(this.javaClass.simpleName, "onCreate")
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.e(this.javaClass.simpleName, "permission not granted")

            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.RECORD_AUDIO),
                    PERMISSIONS_REQUEST_ACCESS_LOCATION)
        } else {
            Log.e(this.javaClass.simpleName, "permission granted")
            isPermissionGranted = true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        Log.e(this.javaClass.simpleName, "onRequestPermissionsResult")
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_LOCATION ->
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Log.e(this.javaClass.simpleName, "PERMISSIONS_REQUEST_ACCESS_LOCATION")
                    isPermissionGranted = true
                }
        }
    }

    protected fun component(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .applicationModule(ApplicationModule(application))
                .interactorModule(InteractorModule())
                .repositoryModule(RepositoryModule())
                .restModule(RestModule())
                .dataMapperModule(DataMapperModule())
                .networkModule(NetworkModule())
                .threadModule(ThreadModule())
                .build()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}