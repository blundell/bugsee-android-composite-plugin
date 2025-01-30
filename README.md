# BugSee Android Gradle composite build plugin

Blog here: https://blog.blundellapps.co.uk/android-bugsee-sdk-ignore-the-docs-do-this-instead/

`/app`
- The main app build

`/build-logic`
- This is the composite gradle build that holds our plugins

`/build-logic/bugsee-plugin`
- This is our custom bugsee gradle plugin
    - adds bugsee to the dependency classpath in debug
    - automatically upload mapping.txt to bugsee
    - make the bugsee App Token available via BuildConfig

`app/src/debug/java/com/blundell/compositebugsee/BugseeInitializer.kt`

- configure bugsee settings before starting

`app/src/debug/AndroidManifest.xml`

- enable bugsee to start in onCreate for debug builds

Note:
- Enabling bugsee on startup is done using androidx.startup
