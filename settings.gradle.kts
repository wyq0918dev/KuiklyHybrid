rootProject.name = "KuiklyHybrid"

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
        mavenLocal()
        // Kuikly
        maven(url = "https://mirrors.tencent.com/nexus/repository/maven-tencent/")
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        // Flutter
        maven(url = "https://storage.googleapis.com/download.flutter.io")
        // JitPack
        maven(url = "https://jitpack.io")
        // Kuikly
        maven(url = "https://mirrors.tencent.com/nexus/repository/maven-tencent/")
    }
}


enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.projectDir.resolve(
    relative = "kuikly_flutter"
).resolve(
    relative = ".android/include_flutter.groovy"
).let { flutter ->
    if (flutter.exists()) {
        apply(from = flutter)
        return@let flutter
    } else throw GradleException(
        "未找到 Flutter 生成的构建脚本, 请先在工程根目录执行 flutter pub get",
    )
}

include(":app")