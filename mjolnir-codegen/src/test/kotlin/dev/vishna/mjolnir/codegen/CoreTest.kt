package dev.vishna.mjolnir.codegen

import dev.vishna.mvel.interpolate
import dev.vishna.stringcode.asResource
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldNotBeEmpty
import org.junit.Test
import java.util.ArrayList

const val domain_models_1 = "/domain_models_1.yaml"
const val domain_models_1_dart = "/domain_models_1.dart"

class CoreTest {

    @Test
    fun `domain models 1 to dart`() = runBlocking<Unit> {
        var template = dartTemplateFile.asResource()
        template.shouldNotBeEmpty()

        template = template.split("\n").let { it.subList(2, it.size) }.joinToString(separator = "\n")

        val models = domain_models_1
            .asResource()
            .asYaml()
            .asModelList(
                lang = "dart",
                packageName = "",
                otherModels = listOf("TextEntity")
            )

        val imports = models
            .flatMap { it.mixins("Import") }
            .map { it.expression }.distinctBy { it }
            .map { """import '$it';""" }

        val output = ArrayList<String>()
        output += imports
        output += models.map { template.interpolate(it)!! }

        output += dartSerializerTemplateFile
            .asResource()
            .interpolate(mapOf(
                "classes" to models.map { it.className }
            ))!!

        val finalOutput = output.joinToString(separator = "\n").dartfmt()

        finalOutput `should be equal to` domain_models_1_dart.asResource()
    }

}