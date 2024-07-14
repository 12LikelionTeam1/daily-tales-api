plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "daily-tales-api"
include("daily-tales-bootstrap")
include("daily-tales-authentication")
include("daily-tales-core")
include("daily-tales-documentation")
include("daily-tales-writing")
include("daily-tales-common")
include("daily-tales-user")
