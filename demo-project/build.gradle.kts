import nu.studer.gradle.credentials.domain.CredentialsContainer

plugins {
    kotlin("plugin.jpa") version "1.9.10"
}

group = "com.marsh"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val credentials: CredentialsContainer by project
val githubUsername: String? = credentials.forKey("github.username")
val githubToken: String? = credentials.forKey("github.token")

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/vitasoftGit/VitaPack-Java")
        credentials {
            username = githubUsername ?: System.getenv("USERNAME")
            password = githubToken ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-mail")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(project(":encoding-util"))
    implementation(project(":file-util"))
    implementation(project(":mail-util"))
    implementation(project(":security-config"))
    implementation(project(":aspectj-config"))
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.bootJar {
    archiveFileName.set("demo-project.jar")
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.withType<Test> {
    useJUnitPlatform()
}
