plugins {
    kotlin("jvm") version "2.0.0"
    id("com.epages.restdocs-api-spec") version "0.18.2"
    `java-library`
}

repositories {
    mavenLocal()
}

dependencies {
    testImplementation(project(":daily-tales-core"))
    testImplementation(project(":daily-tales-bootstrap"))
    testImplementation(project(":daily-tales-authentication"))
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.restdocs:spring-restdocs-restassured")
    testImplementation(files("libs/documentify-core-0.0.1-SNAPSHOT.jar"))
    testImplementation("com.epages:restdocs-api-spec-mockmvc:0.18.2")
    testImplementation("com.epages:restdocs-api-spec-restassured:0.18.2")
    testImplementation("io.rest-assured:spring-mock-mvc:5.4.0")
    testImplementation(kotlin("test"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

openapi3 {
    setServer("https://localhost:8080")
    title = "하루한글 API"
    description = "하루한글 API 명세입니다."
    version = "0.0.1"
    format = "yaml"
    outputDirectory = "src/test/resources/openapi"
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}