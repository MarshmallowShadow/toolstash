plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "toolstash"

include("file-spring-boot")
include("mail-spring-boot")
include("security-spring-boot")
