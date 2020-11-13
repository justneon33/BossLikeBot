plugins {
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "4.0.4"
    kotlin("plugin.serialization") version "1.4.10"
}

group = "ru.bosslike.bot"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://maven.google.com")
    maven("https://dl.bintray.com/y9san9/kotlingang")
    maven("https://dl.bintray.com/whyoleg/ktd")
    maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.github.justneon33:BossLikeAPI:beta-4.1")
    implementation("com.kotlingang.kds:kds:1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:0.3.3")
    implementation("dev.whyoleg.ktd:ktd-api-coroutines-jvm:0.5.0-1.5.4")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "ru.bosslike.bot.MainKt"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}