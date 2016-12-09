package com.collectiveidea.donuts

import android.app.Application
import android.util.Log
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber
import timber.log.Timber.DebugTree


class DonutsApp : Application() {
  override fun onCreate() {
    super.onCreate()
    // This process is dedicated to LeakCanary for heap analysis.
    // You should not init your app in this process.
    if (LeakCanary.isInAnalyzerProcess(this)) return

    LeakCanary.install(this)
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


