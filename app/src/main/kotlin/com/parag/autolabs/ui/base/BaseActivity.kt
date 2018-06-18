package com.parag.autolabs.ui.base

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.parag.autolabs.component.ActivityComponent
import com.parag.autolabs.component.DaggerActivityComponent
import com.parag.autolabs.modules.*

abstract class BaseActivity: AppCompatActivity() {

    protected fun component(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .applicationModule(ApplicationModule(application))
                .repositoryModule(RepositoryModule())
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