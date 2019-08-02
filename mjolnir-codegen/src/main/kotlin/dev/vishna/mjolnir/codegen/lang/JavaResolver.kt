package dev.vishna.mjolnir.codegen.lang

import dev.vishna.mjolnir.codegen.*

import dev.vishna.stringcode.camelize
import dev.vishna.stringcode.applyIf

class JavaResolver : LangResolver() {
    override fun className(model: Model): String = model.name.camelize()

    override fun memberType(field: Field): String {

        fun String.opt(nullableType: String) : String = this.applyIf(field.info.optional) { nullableType }

        return when(field) {
            is StringField -> "String"
            is IntField -> "int".opt("Integer")
            is DateField -> "long".opt("Long")
            is FloatField -> "float".opt("Float")
            is LongField -> "long".opt("Long")
            is BooleanField -> "boolean".opt("Boolean")
            is CustomField -> field.customType.capitalize()
            is ArrayField -> "ArrayList<${memberType(field.field)}>"
        }
    }

    fun ctor(field: Field) : String {
        return with(field.info) {
            when (field) {
                is StringField -> """json.isNull("$name") ? "" : json.optString("$name", "")"""
                is IntField -> """json.optInt("$name", Integer.MIN_VALUE)"""
                is DateField -> """${parent.packageName}.Utils.toMilliseconds(json.optString("$name", ""))"""
                is FloatField -> """json.optDouble($name, Double.NaN)"""
                is LongField -> """json.optLong("$name", Long.MIN_VALUE)"""
                is BooleanField -> """json.optBoolean("$name", false)"""
                is CustomField -> """json.isNull("$name") ? null : new ${field.customType.capitalize()}(json.optJSONObject("$name"))"""
                is ArrayField -> when(field.field) {
                    else -> "// TODO"
                }
            }
        }
    }

    fun dtor(field: Field) : String {
        return with(field.info) {
            when (field) {
                is FloatField,
                is LongField,
                is BooleanField,
                is StringField,
                is IntField -> field.info.name
                is DateField -> """${parent.packageName}.Utils.fromMilliseconds($name)"""
                is CustomField -> """${field.info.name} != null ? ${field.info.name}.toJSON() : null"""
                is ArrayField -> when(field.field) {
                    else -> "// TODO"
                }
            }
        }
    }
}