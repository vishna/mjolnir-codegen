import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath ("com.github.jengelman.gradle.plugins:shadow:5.1.0")
    }
}

plugins {
    application
    kotlin("jvm")
    maven
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

kotlinProject()

application {
    mainClassName = "cli.Main"
}

val shadowJar: ShadowJar by tasks
shadowJar.apply {
    baseName = "mjolnir-codegen"
    archiveName = "mjolnir-codegen.jar"
}

artifacts {
    archives(shadowJar)
}

dependencies {
    compile(project(":mjolnir-codegen"))
    compile("com.github.vishna:patrol:0.0.3")

    testCompile("junit", "junit", "4.12")
    testCompile("org.amshove.kluent:kluent:1.34")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}