plugins {
    java
    id("io.quarkus")    
    id("com.palantir.docker") version "0.33.0" apply true
    id("com.palantir.docker-compose") version "0.33.0" apply true
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-container-image-jib")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-keycloak-authorization")
    implementation("io.quarkus:quarkus-resteasy-jsonb")
    implementation("io.quarkus:quarkus-keycloak-admin-client")
    implementation("io.quarkus:quarkus-reactive-pg-client")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("io.quarkus:quarkus-junit5")
}

group = "com.extia"
version = "0.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}


tasks.register<Copy>("copyDocker"){
    from("src/main/docker/Dockerfile.jvm")
    into(layout.buildDirectory.dir("docker"))

}

docker {
    setName("mat-backend")
    tags("latest")
    setDockerfile(project.file("src/main/docker/Dockerfile.jvm"))
    copySpec.from("build/quarkus-app").into("quarkus-app")    
}
docker {
    setName("mat-backend-bdd")
    tags("latest")
    setDockerfile(project.file("src/main/docker/Dockerfile.bdd"))
}

dockerCompose {
    setTemplate("docker-compose-template.yml")
    setDockerComposeFile("docker-compose.yml")
}