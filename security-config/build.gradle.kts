
version = project.findProperty("version")!!

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
}