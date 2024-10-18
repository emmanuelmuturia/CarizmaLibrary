plugins {
    alias(notation = libs.plugins.kotlinJvm)
    alias(notation = libs.plugins.ktor)
    application
}

group = "emmanuelmuturia.sonux"
version = "1.0.0"
application {
    mainClass.set("emmanuelmuturia.sonux.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(dependencyNotation = projects.shared)
    implementation(dependencyNotation = libs.logback)
    implementation(dependencyNotation = libs.ktor.server.core)
    implementation(dependencyNotation = libs.ktor.server.netty)
    testImplementation(dependencyNotation = libs.ktor.server.tests)
    testImplementation(dependencyNotation = libs.kotlin.test.junit)
}