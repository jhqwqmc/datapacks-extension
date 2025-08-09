plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
    id("com.gradleup.shadow") version "9.0.0-rc2"
    id("de.eldoria.plugin-yml.paper") version "0.7.1"
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenCentral()
}

dependencies {
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.21.4-R0.1-SNAPSHOT")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withSourcesJar()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(21)
    dependsOn(tasks.clean)
}

tasks.processResources {
    filteringCharset = "UTF-8"
    filesMatching(arrayListOf("paper-plugin.yml")) {
        expand(rootProject.properties)
    }
}

paper {
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    main = "cn.gtemc.datapacks.extension.DatapacksExtension"
    bootstrapper = "cn.gtemc.datapacks.extension.DatapacksExtensionBootstrap"
    version = rootProject.properties["version"] as String
    name = "Datapacks-Extension"
    apiVersion = "1.21"
    authors = listOf("jhqwqmc")
    website = "https://github.com/jhqwqmc"
    foliaSupported = true
}

artifacts {
    archives(tasks.shadowJar)
}

tasks {
    shadowJar {
        manifest {
            attributes["paperweight-mappings-namespace"] = "mojang"
        }
        archiveFileName = "${rootProject.name}-paper-plugin-${rootProject.properties["version"]}.jar"
        destinationDirectory.set(file("$rootDir/target"))
    }
}
