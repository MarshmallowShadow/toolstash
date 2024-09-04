plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "toolstash"

include("security-spring-boot")
include("email-spring-boot")
include("file-spring-boot")