rootProject.name = "meet-at-the-office"
include("mat-backend")
include("mat-frontend")
include("mat-keycloak")


pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
}
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion        
    }
}

