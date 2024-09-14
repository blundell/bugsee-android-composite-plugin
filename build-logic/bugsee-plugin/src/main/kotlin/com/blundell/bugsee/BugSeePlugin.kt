package com.blundell.bugsee

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.bugsee.android.gradle.BugseePluginExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project

private const val BUGSEE_APP_TOKEN = "abcdefg-1234-5678-9876-abcdeabcde"

/**
 * For applying the Bugsee, including:
 * - Bugsee enabled in debug
 * - Proguard plugin, that will automatically upload mapping.txt
 * - Making the App Token available via BuildConfig
 */
class BugSeePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            plugins.apply("bugsee")
            val bugsee = extensions.findByType(BugseePluginExtension::class.java)
                ?: throw IllegalStateException("""The "bugsee" plugin needs to be applied to your project first.""")
            with(bugsee) {
                appToken(BUGSEE_APP_TOKEN)
            }

            val libs = extensions.getByType(LibrariesForLibs::class.java)
            with(dependencies) {
                add("debugImplementation", libs.bugsee)
            }

            val androidComponents = extensions.getByName("androidComponents") as ApplicationAndroidComponentsExtension
            androidComponents.finalizeDsl { ac ->
                ac.buildFeatures.buildConfig = true
                ac.buildTypes
                    .filter { it.name.contains("debug") }
                    .forEach { it.buildConfigField("String", "BUGSEE_APP_TOKEN", "\"$BUGSEE_APP_TOKEN\"") }
            }
        }
    }
}
