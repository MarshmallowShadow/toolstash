plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "toolstash"

include("file-util")
include("encoding-util")
include("mail-util")
include("swagger-config")
include("security-config")
include("thymeleaf-config")
include("aspectj-config")
include("demo-project")
