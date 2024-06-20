import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
	kotlin("plugin.lombok") version "2.0.0"
	id("io.freefair.lombok") version "8.1.0"
}

group = "com.hddev"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

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
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.keycloak:keycloak-spring-boot-starter:24.0.5")
	implementation("org.keycloak:keycloak-spring-security-adapter:24.0.5")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("io.jsonwebtoken:jjwt-api:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
