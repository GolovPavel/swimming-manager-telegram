import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("application")
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"
}

group = "ru.golov"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/ruslanys/maven")
}

dependencies {
    // Spring dependencies
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // JetBrains dependencies
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable")

    // Db dependencies
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")

    // Telegram api
    implementation("me.ruslanys.telegraff:telegraff-starter:1.0.0")

    // Other dependencies
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<BootJar>("bootJar") {
    // Need for the scripting-compiler-embeddable dependency to work correctly
    archiveClassifier.set("boot")

    requiresUnpack("**/**kotlin**.jar")
    requiresUnpack("**/**telegraff**.jar")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
