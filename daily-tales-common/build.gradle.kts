dependencies {
    implementation(project(":daily-tales-core"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("com.github.f4b6a3:ulid-creator:5.2.3")
    implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
}