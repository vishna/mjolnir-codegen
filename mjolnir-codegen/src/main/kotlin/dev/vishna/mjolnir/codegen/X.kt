package dev.vishna.mjolnir.codegen

import org.yaml.snakeyaml.Yaml
import java.io.*

internal fun String.asYaml() :  Map<String, Map<String, *>> = Yaml().load(StringReader(this)) as Map<String, Map<String, *>>

typealias ResourcePath = String

inline val <T> T.println
    get() = println(this)

/**
 * Holds current working directory as path.
 */
internal val pwd: FilePath by lazy { System.getProperty("user.dir") }

internal typealias FilePath = String
internal fun FilePath.asFile() : File {
    val file = File(this)
    return if (file.exists()) {
        file
    } else {
        File(pwd, this)
    }
}

fun  Map<String, Map<String, *>>.asModelList(lang: String, packageName: String, otherModels: List<String> = emptyList()) : List<Model> = Model
    .fromYAML(
        lang = lang,
        packageName = packageName,
        otherModels = otherModels,
        yaml = this
    )