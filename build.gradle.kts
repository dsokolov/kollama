plugins {
    kotlin("jvm") version "2.0.0" apply false
    kotlin("plugin.serialization") version "2.0.0" apply false
}

// Load secrets from separate file if it exists
val secretsFile = file("gradle-secrets.properties")
if (secretsFile.exists()) {
    val secrets = java.util.Properties()
    secrets.load(secretsFile.inputStream())
    secrets.forEach { (key, value) ->
        project.extra[key.toString()] = value
    }
}

allprojects {
    group = "me.ilich.kollama"
    version = "1.0.0"
    
    repositories {
        mavenCentral()
    }
}