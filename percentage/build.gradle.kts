import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URI
import java.util.Locale

val snapshot = System.getenv("SNAPSHOT_BUILD").let {
    if (it.isNullOrBlank() || "yes" == it.lowercase(Locale.getDefault())) "-snapshot" else ""
}

version = "%s%s".format(version, snapshot)

kotlin {
    explicitApi()
}

plugins {
    `java-library`
    `maven-publish`
    jacoco
    alias(libs.plugins.jvm)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.sonatype.central.upload)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.math.common)

    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)

    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.libraries)
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(8)
    withSourcesJar()
}

detekt {
    config.setFrom("../detekt.yml")
    buildUponDefaultConfig = true
}

tasks {
    test {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
    }

    jacocoTestReport {
        reports.xml.required = true
        sourceSets(sourceSets.main.get())
        dependsOn("test")
    }

    jar {
        base {
            manifest {
                attributes(mapOf(
                    "Implementation-Title" to "${project.group}.${project.name}",
                    "Implementation-Version" to project.version
                ))
            }
        }
    }

    sonatypeCentralUpload {
        username = System.getenv("MAVEN_CENTRAL_USERNAME")
        password = System.getenv("MAVEN_CENTRAL_PASSWORD")

        archives = files(*jars())
        pom = file("build/publications/pom/pom-default.xml")

        signingKey = System.getenv("GPG_PRIVATE_KEY")
        signingKeyPassphrase = System.getenv("GPG_PRIVATE_KEY_PASSWORD")
        publicKey = System.getenv("GPG_PUBLIC_KEY")

        publishingType = "MANUAL"

        // Allows to run the plugin without rebuilding the project due to available jar files in
        // its publishing directory.
        doFirst { delete("build/sonatype-central-upload") }

        dependsOn("build")
        mustRunAfter("build")
    }

    build {
        dependsOn("detekt", "test", "generatePomFileForPomPublication", "generateJavadocJar")
    }

    withType<DokkaTask>().configureEach {
        dokkaSourceSets {
            named("main") {
                moduleName = "Percentage"
                includes.from("dokka.md")

                sourceLink {
                    localDirectory.set(file("src/main/kotlin"))
                    remoteUrl.set(
                        URI("https://github.com/eriksencosta/math-percentage/tree/trunk/percentage/src/main/kotlin").toURL()
                    )
                    remoteLineSuffix.set("#L")
                }

                externalDocumentationLink {
                    url.set(URI("https://blog.eriksen.com.br/opensource/math-common/").toURL())
                    packageListUrl.set(
                        URI("https://blog.eriksen.com.br/opensource/math-common/-math%20-common/package-list").toURL()
                    )
                }
            }
        }
    }

    register<Copy>("patchDokkaNavigationJavascript") {
        from(layout.buildDirectory.file("dokka/html/scripts/navigation-loader.js"))
        into(layout.buildDirectory.dir("dokka/html"))

        filter { line ->
            if (line.trim() == "document.querySelectorAll(\".overview > a\").forEach(link => {")
                "document.querySelectorAll(\".overview > a\").forEach((link, index) => {"
            else line
        }

        filter { line ->
            if (line.trim() == "link.setAttribute(\"href\", pathToRoot + link.getAttribute(\"href\"));")
                """
                // The first sidebar navigation link doesn't work properly when the files are hosted on a subdirectory
                // like "example.com/dokka/html".
                link.setAttribute("href",
                    pathToRoot + link.getAttribute("href").replace(/^(\/opensource\/.*?\/)(.*)/, "$2")
                );
                """
            else line
        }

        dependsOn("dokkaHtml")
    }

    register<Copy>("dokkaHtmlPatched") {
        description = "Generates documentation in 'html' format with applied patches."
        group = "Documentation"

        from(layout.buildDirectory.file("dokka/html/navigation-loader.js"))
        into(layout.buildDirectory.dir("dokka/html/scripts"))

        dependsOn("patchDokkaNavigationJavascript")
    }

    register<Jar>("generateJavadocJar") {
        description = "Generates the Javadoc jar."
        group = "Documentation"

        from(dokkaJavadoc.flatMap { it.outputDirectory })
        archiveClassifier.set("javadoc")

        dependsOn("dokkaJavadoc")
        mustRunAfter("sourcesJar")
    }

    register<Task>("generateDocs") {
        description = "Generates the project documentation (HTML, Markdown, and Javadoc)."
        group = "Documentation"

        dependsOn("dokkaHtmlPatched", "dokkaGfm", "dokkaJavadoc")
    }

    register<Task>("release") {
        description = "Builds and publishes the library to Maven Central."
        group = "Release"

        dependsOn("build", "sonatypeCentralUpload", "version")
        mustRunAfter("sonatypeCentralUpload", "version")
    }

    register<Task>("version") {
        description = "Prints the version stamp."
        group = "Verification"

        doLast {
            logger.lifecycle("${project.group}:${project.name}:$version")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("pom") {
            pom {
                description = "Percentage calculations made easy"
                url = "https://github.com/eriksencosta/math-percentage"

                developers {
                    developer {
                        name = "Eriksen Costa"
                        url = "https://blog.eriksen.com.br"
                    }
                }

                licenses {
                    license {
                        name = "The Apache Software License, Version 2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }

                scm {
                    url = "git@github.com:eriksencosta/math-percentage.git"
                    tag = "trunk"
                }

                issueManagement {
                    system = "GitHub"
                    url = "https://github.com/eriksencosta/math-percentage/issues"
                }

                ciManagement {
                    system = "GitHub Actions"
                    url = "https://github.com/eriksencosta/math-percentage/actions"
                }

                // Dependencies
                from(components["java"])
            }
        }
    }
}

private fun jars() = arrayOf(jarName(), jarName("javadoc"), jarName("sources"))

private fun jarName(kind: String = "") = "build/libs/$name-%s%s.jar"
    .format(version, if (kind.isNotBlank()) "-$kind" else "")
