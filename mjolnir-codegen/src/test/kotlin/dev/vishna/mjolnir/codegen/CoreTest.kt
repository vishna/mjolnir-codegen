package dev.vishna.mjolnir.codegen

import dev.vishna.stringcode.asResource
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.junit.Test

const val domain_models_1 = "/domain_models_1.yaml"
const val domain_models_1_dart = "/domain_models_1.dart"
const val domain_models_1_photo_java = "/domain_models_1_photo.java"
const val domain_models_1_user_java = "/domain_models_1_user.java"
const val domain_models_1_photo_kt = "/domain_models_1_photo.kt"
const val domain_models_1_user_kt = "/domain_models_1_user.kt"

class CoreTest {

    @Test
    fun `domain models 1 to dart`() = runBlocking<Unit> {
        val dartOutput = domainModelsToDart(domain_models_1.asResource(), emptyList())
        dartOutput `should be equal to` domain_models_1_dart.asResource()
    }

    @Test
    fun `domain models 1 to java`() = runBlocking<Unit> {
        val javaClasses = domainModelsToJava(
            domainModels = domain_models_1.asResource(),
            otherModels = emptyList(),
            packageName = "dev.vishna.test")

        javaClasses.size `should be equal to` 2

        javaClasses[0].model.className `should be equal to` "Photo"
        javaClasses[0].body `should be equal to` domain_models_1_photo_java.asResource()
        javaClasses[1].model.className `should be equal to` "User"
        javaClasses[1].body `should be equal to` domain_models_1_user_java.asResource()
    }

    @Test
    fun `domain models 1 to kotlin`() = runBlocking<Unit> {
        val kotlinClasses = domainModelsToKotlin(
            domainModels = domain_models_1.asResource(),
            otherModels = emptyList(),
            packageName = "dev.vishna.test")

        kotlinClasses.size `should be equal to` 2

        kotlinClasses[0].model.className `should be equal to` "Photo"
        kotlinClasses[0].body `should be equal to` domain_models_1_photo_kt.asResource()
        kotlinClasses[1].model.className `should be equal to` "User"
        kotlinClasses[1].body `should be equal to` domain_models_1_user_kt.asResource()
    }

}