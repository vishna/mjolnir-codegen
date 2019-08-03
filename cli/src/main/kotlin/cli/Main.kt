@file:JvmName("Main")
package cli

import dev.vishna.mjolnir.codegen.bootstrapMjolnirPatrolConfig
import dev.vishna.mjolnir.codegen.generateCode
import dev.vishna.patrol.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ConcurrentHashMap

fun main(args: CommandArgs) = args.patrol {

    val inspectionJobs = ConcurrentHashMap<String, Job>()

    name {
        "mjolnir-codegen"
    }

    help {
        "Mjolnir Code Generator."
    }

    onInspection { scope, watchPoint, dryRun, runOnce ->
        scope.launch {
            generateCode(
                name = watchPoint.name,
                source = watchPoint.source,
                target = requireNotNull(watchPoint["target"] as String?) { "target value not provided in $watchPoint" },
                lang = requireNotNull(watchPoint["lang"] as String?) { "lang value not provided in $watchPoint" },
                otherModels = (watchPoint["other_models"] as? List<*>)?.map { it.toString() } ?: emptyList(),
                dryRun = dryRun
            )
        }.apply {
            inspectionJobs[watchPoint.name]?.cancel()
            inspectionJobs[watchPoint.name] = this
            if (runOnce) {
                runBlocking {
                    join()
                }
            }
        }
    }

    bootstrap(::bootstrapMjolnirPatrolConfig)
}