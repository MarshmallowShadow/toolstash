import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"
    kotlin("kapt") version "1.9.10"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.springframework.boot") version "3.3.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
    id("maven-publish")
}

repositories {
    mavenCentral()
}

val jar: Jar by tasks
jar.enabled = false

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "maven-publish")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    repositories {
        mavenCentral()
}

    group = "com.marsh.toolstash"

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("commons-io:commons-io:2.7")

        kapt("org.springframework.boot:spring-boot-configuration-processor")
        
        testImplementation("org.jetbrains.kotlin:kotlin-test")
    }

    val shouldPublish = project.hasProperty("shouldPublish") && project.property("shouldPublish") == "true"

    if(shouldPublish) {
        publishing {
            repositories {
                maven {
                    name = "GitHubPackages"
                    url = uri("https://maven.pkg.github.com/MarshmallowShadow/toolstash")
                    credentials {
                        username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                        password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
                    }
                }
            }
            publications {
                register<MavenPublication>("gpr") {
                    from(components["java"])
                }
            }
        }
    }

    val jar: Jar by tasks
    jar.enabled = true

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
    tasks.test {
        useJUnitPlatform()
    }
    kotlin {
        jvmToolchain(17)
    }
}