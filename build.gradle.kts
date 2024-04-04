import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask
import org.jlleitschuh.gradle.ktlint.tasks.KtLintFormatTask
import org.jooq.codegen.gradle.CodegenTask
import org.jooq.meta.jaxb.Jdbc

plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.liquibase.gradle") version "2.2.0" apply true
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.6" apply true
    id("org.jooq.jooq-codegen-gradle") version "3.19.6"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
}

group = "tech.pic.portal"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("jakarta.persistence:jakarta.persistence-api")

    implementation("org.postgresql:postgresql")
    implementation("org.liquibase:liquibase-core")
    implementation("org.jooq:jooq-codegen:3.19.6")
    implementation("org.jooq:jooq-kotlin:3.19.6")
    implementation("org.jooq:jooq:3.19.6")

    implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    jooqCodegen("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
    jooqCodegen("org.jooq:jooq-meta-extensions:3.19.6")
    jooqCodegen("org.jooq:jooq-meta-kotlin:3.19.6")
    jooqCodegen("org.postgresql:postgresql")

    liquibaseRuntime("org.liquibase:liquibase-core")
    liquibaseRuntime("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("io.kotest:kotest-assertions-core:2.4.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.withType<KotlinCompile> {
    dependsOn("jooqCodegenMain")
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KtLintFormatTask> {
    dependsOn("jooqCodegenMain")
    exclude("**/generated/**")
}

tasks.withType<KtLintCheckTask> {
    dependsOn("jooqCodegenMain")
    exclude("**/generated/**")
}

detekt {
    buildUponDefaultConfig = true
}

task("liquibaseUpdate") {
    doFirst() {
        liquibase {
            activities.register("main") {
                val dbUrl = System.getenv("PSQL_URL")
                val dbUser = System.getenv("PSQL_USER")
                val dbPass = System.getenv("PSQL_PASSWORD")
                this.arguments = mapOf(
                    "logLevel" to "info",
                    "changeLogFile" to "src/main/resources/migrations/db.changelog-master.xml",
                    "url" to dbUrl,
                    "username" to dbUser,
                    "password" to dbPass
                )
            }
            runList = "main"
        }
    }
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "17"
    reports {
        xml.required = true
        html.required = true
        txt.required = true
        sarif.required = true
        md.required = true
    }
    basePath = rootDir.absolutePath
    dependsOn("jooqCodegenMain")
    exclude("**/generated/**")
}

jooq {
    version = "3.19.6"
    configuration { }

    executions {
        create("main") {
            configuration {
                logging = org.jooq.meta.jaxb.Logging.DEBUG
                jdbc = Jdbc()

                jdbc.url = System.getenv("PSQL_URL")
                jdbc.user = System.getenv("PSQL_USER")
                jdbc.password = System.getenv("PSQL_PASSWORD")

                generator {
                    name = "org.jooq.codegen.KotlinGenerator"

                    database {
                        inputSchema = "public"
                    }

                    generate {
                        isPojosAsKotlinDataClasses = true // use data classes
                        isImplicitJoinPathsToMany = false
                    }

                    target {
                        packageName = "tech.pic.portal.jvm.generated"
                        directory = "$projectDir/src/main/kotlin/"
                    }
                }
            }
        }
    }
}

tasks.withType<CodegenTask> {
    dependsOn("liquibaseUpdate")
}
