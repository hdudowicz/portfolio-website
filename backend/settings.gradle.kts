rootProject.name = "backend"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
    }
}
