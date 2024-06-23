import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
    kotlin("plugin.lombok") version "2.0.0"
    id("io.freefair.lombok") version "8.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

group = "com.hddev"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation(kotlin("reflect"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("com.mysql:mysql-connector-j:8.4.0")

    // AUTH
    implementation("org.springframework.boot:spring-boot-starter-security:3.3.0")
    implementation("org.keycloak:keycloak-spring-boot-starter:24.0.5")
    implementation("org.keycloak:keycloak-spring-security-adapter:24.0.5")

    // Hibernate dependencies
    implementation("org.hibernate.orm:hibernate-core:6.5.2.Final")
    implementation("org.hibernate.common:hibernate-commons-annotations:7.0.1.Final")

    implementation("com.fasterxml:classmate:1.7.0")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
}

ktlint {
    outputToConsole.set(true)
    enableExperimentalRules.set(false)
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    enabled = false // Disable testing
}
