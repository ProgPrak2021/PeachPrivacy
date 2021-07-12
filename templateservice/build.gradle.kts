import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
}

group = "com.peachprivacy"
version = "1.1.4"

repositories {
    mavenCentral()
    /*maven {
        url = uri("https://maven.pkg.github.com/Transparency-Information-Language/java-tilt")
        credentials {
            // TODO: Proper GPR setup for GitHub access to "cloud.implementation.tilt:tilt:0.0.1" instead of JitPack
            username = project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key")?.toString() ?: System.getenv("TOKEN")
        }
    }*/
    maven("https://jitpack.io")
}

extra["springCloudVersion"] = "2020.0.2"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.worldturner.medeia:medeia-validator-jackson:1.1.1")

    // implementation("cloud.implementation.tilt:tilt:0.0.1")
    implementation("com.github.Transparency-Information-Language:java-tilt:3ce9d30")

    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("io.springfox:springfox-swagger2:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
    imageName = "europe-west3-docker.pkg.dev/peachprivacy/peachprivacy/templateservice:${project.version}"
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