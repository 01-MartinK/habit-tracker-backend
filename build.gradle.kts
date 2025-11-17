plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

group = "com.habit"
version = "0.0.5"

application {
    applicationDefaultJvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.jackson)
    implementation(libs.ktor.server.default.headers)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.slf4j.logger)
    implementation(libs.jetbrains.exposed.core)
    implementation(libs.jetbrains.exposed.jdbc)
    implementation(libs.jetbrains.exposed.dao)
    implementation(libs.h2database.h2)
    implementation(libs.zaxxer.hikaricp)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.mockk)
}
