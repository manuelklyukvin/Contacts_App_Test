rootProject.name = "Contacts App"

val app = ":app"
val core = ":core"
val features = ":features"

val main = "$features:main"

include(app)
include(
    core,
    presentationModule(core),
    domainModule(core),
    infrastructureModule(core),
    diModule(core)
)
include(features)

include(
    main,
    presentationModule(main),
    domainModule(main),
    dataModule(main),
    infrastructureModule(main),
    diModule(main)
)

fun presentationModule(module: String) = "$module:presentation"
fun domainModule(module: String) = "$module:domain"
fun dataModule(module: String) = "$module:data"
fun infrastructureModule(module: String) = "$module:infrastructure"
fun diModule(module: String) = "$module:di"