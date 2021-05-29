import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
}

group = "com.peachprivacy"
version = "1.0.2"

repositories {
    mavenCentral()
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api("org.springframework.boot:spring-boot-starter-security:2.4.5")
    api("org.springframework.boot:spring-boot-starter-data-jpa:2.4.5")
    api("org.springframework.boot:spring-boot-starter-web:2.4.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}