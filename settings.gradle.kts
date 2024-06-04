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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://github.com/psiegman/mvn-repo/raw/master/releases")
            credentials {
                username = "fzry18" // Ganti dengan username GitHub Anda
                password = System.getenv("GITHUB_TOKEN") // Merujuk ke variabel lingkungan GITHUB_TOKEN
            }
        }
        jcenter()
    }
}

rootProject.name = "SellucoseBook"
include(":app")
 