plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.gradle.plugin-publish")
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.kotlin.kotlinGradlePlugin)
    implementation(libs.bugsee.bugseeAndroidGradleplugin)

    compileOnly(libs.android.application.androidApplicationGradlePlugin)
    // https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("com.blundell.bugsee.plugin") {
            id = "com.blundell.bugsee.plugin"
            implementationClass = "com.blundell.bugsee.BugSeePlugin"
        }
    }
}
