plugins {
    id("com.palantir.docker-compose") version "0.33.0" apply true
}

group = "com.extia"
version = "0.0.0-SNAPSHOT"

dockerCompose {
    setTemplate("docker-compose-template.yml")
    setDockerComposeFile("docker-compose.yml")
}