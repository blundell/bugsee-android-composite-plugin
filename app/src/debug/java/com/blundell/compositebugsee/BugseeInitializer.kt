package com.blundell.compositebugsee

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.bugsee.library.Bugsee
import com.bugsee.library.events.BugseeLogLevel

class BugseeInitializer : Initializer<Unit> {

    /**
     * options https://docs.bugsee.com/sdk/android/configuration/
     */
    override fun create(context: Context) {
        val options = HashMap<String, Any>()
        options[Bugsee.Option.CaptureLogs] = true
        options[Bugsee.Option.LogLevel] = BugseeLogLevel.Verbose
        options[Bugsee.Option.ScreenshotEnabled] = true
        Bugsee.launch(context.applicationContext as Application, BuildConfig.BUGSEE_APP_TOKEN, options)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
