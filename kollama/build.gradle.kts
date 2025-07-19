plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    `java-library`
    `maven-publish`
    signing
}

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
        create<MavenPublication>("maven") {
            from(components["java"])
            
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            
            pom {
                name.set("Kollama")
                description.set("Kotlin client library for Ollama API")
                url.set("https://github.com/ilich/kollama")
                
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                
                developers {
                    developer {
                        id.set("ilich")
                        name.set("Dmitry I. Sokolov")
                        email.set("d.i.sokolov@vk.com")
                    }
                }
                
                scm {
                    connection.set("scm:git:git://github.com/ilich/kollama.git")
                    developerConnection.set("scm:git:ssh://github.com:ilich/kollama.git")
                    url.set("https://github.com/ilich/kollama")
                }
            }
        }
    }
    
    repositories {
        maven {
            name = "CentralPublishingPortal"
            url = uri("https://central.sonatype.com/api/v1/publisher")
            credentials {
                username = project.findProperty("centralUsername") as String?
                password = project.findProperty("centralPassword") as String?
            }
        }
    }
}

signing {
    val signingKeyId = project.findProperty("signing.keyId") as String?
    val signingPassword = project.findProperty("signing.password") as String?
    val signingSecretKeyRingFile = project.findProperty("signing.secretKeyRingFile") as String?
    
    if (signingKeyId != null) {
        useInMemoryPgpKeys(signingKeyId, signingSecretKeyRingFile, signingPassword)
        sign(publishing.publications["maven"])
    }
} 