plugins {
    id("org.springframework.boot") version "3.3.1"
}

dependencies {
    implementation(project(":daily-tales-core"))
    implementation(project(":daily-tales-authentication"))
    implementation(project(":daily-tales-user"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("mysql:mysql-connector-java:8.0.33")
}

tasks {
    bootJar {
        archiveBaseName.set(project.rootProject.name)
        archiveVersion.set("")
        archiveClassifier.set("")
        destinationDirectory.set(rootProject.layout.buildDirectory.asFile.get().resolve("output"))
    }
}