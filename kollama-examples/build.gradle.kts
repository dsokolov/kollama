plugins {
    kotlin("jvm") version "2.0.0"
    application
}

application {
    mainClass.set("io.github.dsokolov.kollama.examples.MainKt")
}

dependencies {
    // Local project dependency - for development
    //implementation(project(":kollama"))

    // Local maven dependency
    //implementation("io.github.dsokolov:kollama:0.0.1-SNAPSHOT")

    // Maven Central dependency - use it for production
    implementation("io.github.dsokolov:kollama:0.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}


kotlin {
    jvmToolchain(21)
}