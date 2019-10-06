plugins {
    kotlin("jvm") version "1.3.50"
    id("template-snippets")
}

group = "dev.nies"
version = "0.0.1"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    api("com.beust:klaxon:5.0.13")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
