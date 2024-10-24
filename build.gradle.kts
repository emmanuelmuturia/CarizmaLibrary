plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(notation = libs.plugins.androidApplication) apply false
    alias(notation = libs.plugins.androidLibrary) apply false
    alias(notation = libs.plugins.jetbrainsCompose) apply false
    alias(notation = libs.plugins.compose.compiler) apply false
    alias(notation = libs.plugins.kotlinJvm) apply false
    alias(notation = libs.plugins.kotlinMultiplatform) apply false
    alias(notation = libs.plugins.ktlint)
    alias(notation = libs.plugins.detekt)
    alias(notation = libs.plugins.spotless)
}

subprojects {
    apply(plugin = rootProject.libs.plugins.ktlint.get().pluginId)
    ktlint {
        verbose.set(true)
        android.set(true)
        filter {
            exclude("**/generated/**")
        }
    }

    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            licenseHeaderFile(rootProject.file("$rootDir/spotless/copyright.kt"))
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
            licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
        }
    }
}

apply(plugin = rootProject.libs.plugins.detekt.get().pluginId)
detekt {
    parallel = true
}