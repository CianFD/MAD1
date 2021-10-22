package org.wit.landmark.main

import android.app.Application
import org.wit.landmark.models.LandmarkMemStore
import org.wit.landmark.models.LandmarkModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {


    val landmarks = LandmarkMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Historical Landmarks of Ireland Activated...")
    }
}
