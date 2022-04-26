plugins {
    id("com.palantir.docker") version "0.33.0" apply false
    id("com.palantir.docker-compose") version "0.33.0" apply true    
}



tasks.register<Exec>("openWeb"){
    var os = org.gradle.internal.os.OperatingSystem.current()
    if (os.isWindows()) {
        commandLine("cmd", "/c", "start http://localhost:3000")
    } else if (os.isMacOsX()) {
        commandLine("open", "http://localhost:3000")
    }
}

tasks.register<Exec>("openBackend"){
    var os = org.gradle.internal.os.OperatingSystem.current()
    if (os.isWindows()) {
        commandLine("cmd", "/c", "start http://localhost:8090")
    } else if (os.isMacOsX()) {
        commandLine("open", "http://localhost:8090")
    }
}


tasks.register<GradleBuild>("killThemAll") {
     tasks = listOf("dockerClean","dockerComposeDown", "mat-backend:clean")
     doLast{
     println("\u001b[31m###################################################################################\u001b[0m")
     println("\u001b[33m All clean up, Goodbye\u001b[0m")
     println("\u001b[31m###################################################################################\u001b[0m")
     }
}

tasks.register<GradleBuild>("prepareBackend"){
      tasks = listOf()
}


tasks.register<GradleBuild>("initAll") {    
    tasks = listOf(
        "mat-backend:clean",
        "mat-backend:quarkusBuild",
        "generateDockerCompose",    
        "docker"     
        )
     
}
tasks.register<GradleBuild>("initThemAll") {
    tasks = listOf("initAll","dockerComposeUp")
    doLast{        
     println("\u001b[31m###################################################################################\u001b[0m")
     println("\u001b[33m Welcome into Meet At The Office by Extia\u001b[0m")
     println("\u001b[33m You can attach web app with `gradle openweb` or backend with `gradle backendAttach`\u001b[0m")
     println("\u001b[33m To clean up your environment you can use `gradle killThemAll`\u001b[0m")
     println("\u001b[31m###################################################################################\u001b[0m")
     }
}

tasks.register<GradleBuild>("backendAttach") {
     tasks = listOf("openBackend","mat-backend:quarkusRemoteDev")      
}

dockerCompose {
    setTemplate("docker-compose-template.yml")
    setDockerComposeFile("docker-compose.yml")
}
