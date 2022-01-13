import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.10"
    java
    id("application")
    id("com.github.johnrengelman.shadow") version "4.0.4"
}

group = "kim.bifrost.rain.badapple"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "kim.bifrost.rain.badapple.BadAppleKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

// Shadow Kotlin运行环境
tasks.withType<ShadowJar> {
    mergeServiceFiles()
    manifest {
        attributes(mapOf("Main-Class" to "kim.bifrost.rain.badapple.BadAppleKt"))
    }
    dependencies {
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
        include(dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm"))
    }
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}