val secretsFile = file("gradle-secrets.properties")
if (secretsFile.exists()) {
    val secrets = java.util.Properties()
    secrets.load(secretsFile.inputStream())
    secrets.forEach { (key, value) ->
        project.extra[key.toString()] = value
    }
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}
