plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "toolstash"

include("file-util")
include("mail-util")
include("encoding-util")
include("swagger-config")
include("security-config")
include("jwt-utils")
include("thymeleaf-config")
