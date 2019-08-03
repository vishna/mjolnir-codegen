package dev.vishna.mjolnir.codegen

import dev.vishna.stringcode.asResource
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.junit.Test

const val domain_models_1 = "/domain_models_1.yaml"
const val domain_models_1_dart = "/domain_models_1.dart"

class CoreTest {

    @Test
    fun `domain models 1 to dart`() = runBlocking<Unit> {
        val dartOutput = domainModelsToDart(domain_models_1.asResource(), emptyList())
        dartOutput `should be equal to` domain_models_1_dart.asResource()
    }

}