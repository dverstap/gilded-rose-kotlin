import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
}

kotlin {
    jvmToolchain(17)
}

group = "com.gildedrose"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(platform("io.cucumber:cucumber-bom:7.13.0"))
    testImplementation("io.cucumber:cucumber-java8")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}
