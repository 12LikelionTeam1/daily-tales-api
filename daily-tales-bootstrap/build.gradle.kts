plugins {
    id("org.springframework.boot") version "3.3.1"
    id("com.google.cloud.tools.jib") version "3.1.2"
}

dependencies {
    implementation(project(":daily-tales-core"))
    implementation(project(":daily-tales-common"))
    implementation(project(":daily-tales-authentication"))
    implementation(project(":daily-tales-writing"))
    implementation(project(":daily-tales-user"))

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks {
    bootJar {
        archiveBaseName.set(project.rootProject.name)
        archiveVersion.set("")
        archiveClassifier.set("")
        destinationDirectory.set(rootProject.layout.buildDirectory.asFile.get().resolve("output"))
    }
}

jib {
    val imageTag = System.getenv("IMAGE_TAG")
    val serverPort = System.getenv("SERVER_PORT")
    val activeProfile = System.getenv("ACTIVE_PROFILE")
    val imageName = System.getenv("IMAGE_NAME")
    from {
        image = "openjdk:17-alpine"
    }
    to {
        image = "$imageName:$imageTag"
        tags = setOf("latest", imageTag)
    }
    container {
        jvmFlags = listOf(
            "-Xms512m",
            "-Xmx512m",
            "-Dserver.port=$serverPort",
            "-Dspring.profiles.active=$activeProfile"
        )
        ports = listOf(serverPort)
    }
}