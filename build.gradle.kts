plugins {
    java
    kotlin("jvm")
}

kotlin {
    jvmToolchain(26)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.mongodb:mongodb-driver-bom:5.8.0"))
    implementation("org.mongodb:mongodb-driver-kotlin-sync")

    implementation("org.mongodb:bson-kotlinx")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.0")

    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")

    testImplementation("org.junit.jupiter:junit-jupiter-api:6.1.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.1.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileKotlin {
}
