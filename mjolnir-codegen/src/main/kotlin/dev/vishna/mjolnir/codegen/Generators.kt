package dev.vishna.mjolnir.codegen

import dev.vishna.mvel.interpolate
import dev.vishna.stringcode.asResource

suspend fun domainModelsToDart(domainModels: String, otherModels: List<String>) : String {
    val dartTemplate = dartTemplateFile.asResource()

    val models = domainModels
        .asYaml()
        .asModelList(
            lang = "dart",
            packageName = "",
            otherModels = otherModels
        )

    val imports = models
        .flatMap { it.mixins("Import") }
        .map { it.expression }.distinctBy { it }
        .map { """import '$it';""" }

    val output = ArrayList<String>()
    output += """/// Generated file, DO NOT EDIT!!!"""
    output += imports
    output += models.map {
        requireNotNull(dartTemplate.interpolate(it)) {
            "failed interpolating: $dartTemplate \n for: \n$it\n"
        }
    }

    output += dartSerializerTemplateFile
        .asResource()
        .interpolate(mapOf(
            "classes" to models.map { it.className }
        ))!!

    return output.joinToString(separator = "\n").dartfmt()
}