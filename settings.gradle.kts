rootProject.name = "Contacts App"

include(":app")
include(
    ":core",
    ":core:presentation",
    ":core:domain"
)
include(":features")

include(
    ":features:main",
    ":features:main:presentation",
    ":features:main:domain",
    ":features:main:data",
    ":features:main:di"
)