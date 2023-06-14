pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Dota2"
include(":app")
include(":utils")
include(":screens:main:api")
include(":screens:main:impl")
include(":entities")
include(":domain:api")
include(":domain:impl")
include(":domain:feature")
include(":screens:main:feature")
include(":screens:player:api")
include(":screens:player:feature")
include(":screens:player:impl")
