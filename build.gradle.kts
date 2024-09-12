import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.springframework.boot") version "3.3.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.0"

    id("maven-publish")
}

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

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val jar: Jar by tasks
jar.enabled = false

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")


    group = "com.marsh"
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("commons-io:commons-io:2.7")
        
        testImplementation("org.jetbrains.kotlin:kotlin-test")
    }

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

project(":file-spring-boot") {
    val jar: Jar by tasks
    jar.archiveFileName.set("file-spring-boot.jar")
    jar.enabled = true
}

project(":mail-spring-boot") {
    val jar: Jar by tasks
    jar.archiveFileName.set("mail-spring-boot.jar")
    jar.enabled = true
}

project(":security-spring-boot") {
    val jar: Jar by tasks
    jar.archiveFileName.set("security-spring-boot.jar")
    jar.enabled = true
}

project(":swagger-spring-boot-autoconfigure") {
    val jar: Jar by tasks
    jar.archiveFileName.set("swagger-spring-boot-autoconfigure.jar")
    jar.enabled = true
}