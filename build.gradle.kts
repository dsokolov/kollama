plugins {
    kotlin("jvm") version "2.0.0" apply false
    kotlin("plugin.serialization") version "2.0.0" apply false
}

allprojects {
    group = "me.ilich.kollama"
    version = "1.0-SNAPSHOT"
    
    repositories {
        mavenCentral()
    }
}