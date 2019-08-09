package dev.vishna.mjolnir.codegen

import dev.vishna.emojilog.std.*
import dev.vishna.stringcode.asResource
import dev.vishna.stringcode.saveAs
import kotlinx.coroutines.*
import java.io.File
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

internal val log by lazy { defaultLogger() }

fun bootstrapMjolnirPatrolConfig(patrolFile: File) = if (File(pwd, "pubspec.yaml").exists()) {
    log.alert.."${patrolFile.name} not found, creating one for you..."
    mjolnirCodegen.asResource().saveAs(patrolFile.absolutePath)
    log.save.."${patrolFile.name} created, please edit it"
    true
} else {
    false
}

suspend fun generateCode(name: String, source: String, lang: String, target: String, otherModels: List<String>, dryRun: Boolean) = supervisorScope {

    if (source.isBlank()) {
        throw IllegalStateException("No source value provided for $name")
    }

    val mjolnirFile = source.asFile()

    if (!mjolnirFile.exists()) {
        throw IllegalStateException("Provided source file for $name doesn't exist")
    }

    val domainModels = mjolnirFile.readText()

    when (lang) {
        "dart" -> {
            domainModelsToDart(domainModels, otherModels).saveToTarget(target, dryRun)
        }
        else -> throw IllegalArgumentException("lang=$lang not supported by this generator")
    }
}

private fun String.saveToTarget(target: String, dryRun: Boolean) {
    if (dryRun) {
        log.tool..target
        "------------------------------".println
        println
        "------------------------------".println
    } else {
        // save to the target location
        val targetFile = target.asFile()
        val oldHashCode = if (!targetFile.exists()) {
            File(targetFile.parent).mkdirs()
            0
        } else {
            targetFile.readText().hashCode()
        }

        if (oldHashCode != hashCode()) {
            saveAs(target)
            log.save..target
        } else {
            log.skip..target
        }
    }
}