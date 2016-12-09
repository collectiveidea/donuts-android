package com.collectiveidea.donuts

import android.app.Application
import android.util.Log
import timber.log.Timber
import timber.log.Timber.DebugTree


class DonutsApp : Application() {
  override fun onCreate() {
    super.onCreate()

    Timber.plant(loggingTree)
  }

  internal val loggingTree = if (BuildConfig.DEBUG) DebugTree() else object : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
      if (priority == Log.VERBOSE || priority == Log.DEBUG) {
        return
      }

      TODO("Set up crash reporting")
    }
  }
}


