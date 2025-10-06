plugins {
    id("java")
}

group = "uk.co.nikodem"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    flatDir {
        dirs("libs")
    }
}

dependencies {
    implementation("team.unnamed:creative-api:1.8.4-SNAPSHOT")
    implementation("team.unnamed:creative-serializer-minecraft:1.8.4-SNAPSHOT")

    // creative deps
    implementation("net.kyori:adventure-api:4.14.0")
    implementation("net.kyori:adventure-key:4.9.3")
    implementation("net.kyori:adventure-text-serializer-legacy:4.14.0")
    implementation("com.google.code.gson:gson:2.11.0")
}