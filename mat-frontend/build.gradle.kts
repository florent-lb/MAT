plugins {
  id("com.github.node-gradle.node") version "3.2.1"
  id("com.palantir.docker") apply true
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
