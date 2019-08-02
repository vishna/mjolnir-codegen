package dev.vishna.mjolnir.codegen.lang

import dev.vishna.mjolnir.codegen.*

import dev.vishna.stringcode.camelize
import dev.vishna.stringcode.applyIf

class KotlinResolver : LangResolver() {
    override fun className(model: Model): String = model.name.camelize()

    override fun memberType(field: Field): String {
        fun String.opt() : String = this.applyIf(field.info.optional) { "$this?" }

        return when(field) {
            is StringField -> "String".opt()
            is IntField -> "Int".opt()
            is DateField -> "Long".opt()
            is FloatField -> "Float".opt()
            is LongField -> "Long".opt()
            is BooleanField -> "Boolean".opt()
            is CustomField -> field.customType.capitalize().opt()
            is ArrayField -> "List<${memberType(field.field)}>"
        }
    }
}