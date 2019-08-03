import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

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
    compile("com.github.vishna:patrol:master-SNAPSHOT")
}