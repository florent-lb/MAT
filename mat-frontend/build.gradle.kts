plugins {
  id("com.github.node-gradle.node") version "3.2.1"
        id("com.palantir.docker") version "0.33.0" apply true
        id("com.palantir.docker-run") version "0.33.0" apply true
}


node {
  version.set("16.14.2")
    npmVersion.set("8")
    //yarnVersion.set("")
    npmInstallCommand.set("install")
    distBaseUrl.set("https://nodejs.org/dist")
    download.set(false)
    workDir.set(file("${project.projectDir}/.cache/nodejs"))
    npmWorkDir.set(file("${project.projectDir}/.cache/npm"))
    yarnWorkDir.set(file("${project.projectDir}/.cache/yarn"))
    nodeProjectDir.set(file("${project.projectDir}"))
}

docker {
    files("package.json","package-lock.json")
    setName("mat-frontend")
    tags("latest")   

}

dockerRun {
    setName("mat-frontend")
    setImage("mat-frontend")
    ports("3000:3000")
    setClean(true)
    volumes(mutableMapOf<Any, String>().apply {
    
    put("${project.projectDir}","/app")

  })
} 