buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Libs.buildGradle)
        classpath(Libs.kotlinGradlePlugin)
        classpath(Libs.hiltAndroidGradlePlugin)
    }
}

plugins {
    id("de.fayard.buildSrcVersions") version Versions.buildSrcVersionsGradlePlugin
}

allprojects {
    repositories {
        maven { url = uri("https://jitpack.io") }
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
