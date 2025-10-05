plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("kapt") version "2.1.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}
dependencies {
    implementation("com.google.dagger:dagger:2.55")
    kapt("com.google.dagger:dagger-compiler:2.55")
    testImplementation(kotlin("test"))
}



tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}