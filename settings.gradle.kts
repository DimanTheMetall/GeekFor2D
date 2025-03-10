pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":app")
include(":core")
include(":ui")
include(":navigationController")
include(":features:feed")
include(":features:favorite")
include(":data")
include(":domain:entities")
include(":features:cards:basecards")
include(":features:cards:manga")
