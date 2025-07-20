import org.jreleaser.model.Active

plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    `java-library`
    `maven-publish`
    id("org.jreleaser") version "1.19.0"
}

group = properties["GROUP"].toString()
version = properties["VERSION_NAME"].toString()
description = "Ollama Kotlin Library"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    
    // Ktor Client dependencies
    implementation("io.ktor:ktor-client-core:2.3.4")
    implementation("io.ktor:ktor-client-cio:2.3.4")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    implementation("io.ktor:ktor-client-logging:2.3.4")
    testImplementation("io.ktor:ktor-client-mock:2.3.4")
    
    // kotlinx.serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    
    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

// POM metadata
java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = "kollama"

            pom {
                name.set("kollama")
                description.set(project.description)
                url.set("https://github.com/dsokolov/kollama")
                issueManagement {
                    url.set("https://github.com/dsokolov/kollama/issues")
                }
                scm {
                    url.set("https://github.com/dsokolov/kollama")
                    connection.set("scm:git://github.com/dsokolov/kollama.git")
                    developerConnection.set("scm:git://github.com/dsokolov/kollama.git")
                }
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("ilichme")
                        name.set("Dmitry I. Sokolov")
                        email.set("d.i.sokolov@vk.com")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            setUrl(layout.buildDirectory.dir("staging-deploy"))
        }
    }
}

jreleaser {
    project {
        inceptionYear = "2025"
        author("Dmitry I. Sokolov")
    }
    release {
        github {
            skipRelease = true
            skipTag = true
        }
    }
    signing {
        active = Active.ALWAYS
        armored = true
        verify = true
    }
    deploy {
        maven {
            mavenCentral.create("sonatype") {
                active = Active.ALWAYS
                verifyPom = false
                url = "https://central.sonatype.com/api/v1/publisher"
                retryDelay = 60
                stagingRepository(layout.buildDirectory.dir("staging-deploy").get().toString())
                setAuthorization("Basic")
            }
        }
    }
    gitRootSearch = true
}
