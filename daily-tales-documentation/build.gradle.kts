plugins {
    kotlin("jvm") version "2.0.0"
    `java-library`
}

repositories {
    mavenLocal()
}

dependencies {
    implementation(project(":daily-tales-core"))
    implementation(files("libs/documentify-core-0.0.1-SNAPSHOT.jar"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}