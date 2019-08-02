package dev.vishna.mjolnir.codegen.lang

import dev.vishna.mjolnir.codegen.Field
import dev.vishna.mjolnir.codegen.Member
import dev.vishna.mjolnir.codegen.Mixin
import dev.vishna.mjolnir.codegen.Model


abstract class LangResolver {

    fun type(member: Member): String {
        return when(member) {
            is Field -> memberType(member)
            is Mixin -> "Mixin"
        }
    }

    abstract fun memberType(field: Field) : String

    abstract fun className(model: Model) : String

    companion object {
        operator fun invoke(lang: String) : LangResolver {
            return when(lang) {
                "kotlin" -> KotlinResolver()
                "java" -> JavaResolver()
                "dart" -> DartResolver()
                else -> throw IllegalStateException("Language $lang is not supported yet")
            }
        }
    }

    open val specialNames: List<String>
        get() = emptyList()
}