plugins {
    java
    id("com.gradleup.shadow") version "9.0.0-beta15"
}

group = "io.github.dexrnzacattack"
version = "1.4.0"
description = "Discord bridge/relay plugin for both old and new release (plus some Beta) versions"
java.toolchain.languageVersion = JavaLanguageVersion.of(8)

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://nexus.scarsz.me/content/repositories/releases")
    maven("https://repo.md-5.net/content/groups/public")
}

dependencies {
    implementation("com.google.code.gson:gson:2.13.0")
    implementation("club.minnced:discord-webhooks:0.8.4")
    implementation("net.dv8tion:JDA:5.5.1")
    implementation("me.scarsz.jdaappender:jda5:1.2.3")
    implementation("org.slf4j:slf4j-jdk14:2.0.17")
    compileOnly("org.bukkit:bukkit:1.4.7-R1.0")
}

tasks {
    shadowJar {
        archiveClassifier = ""
        exclude("META-INF")
        exclude("com/legacyminecraft/**")

        exclude("natives/**")
        exclude("com/sun/jna/**")
        exclude("com/google/crypto/tink/**")
        exclude("com/google/protobuf/**")
        exclude("google/protobuf/**")
        exclude("club/minnced/opus/util/*")
        exclude("tomp2p/opuswrapper/*")
    }

    build {
        dependsOn(shadowJar)
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    processResources {
        filesMatching("plugin.yml") {
            expand("version" to version)
        }
    }
}