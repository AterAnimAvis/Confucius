plugins {
    java
    `maven-publish`
}

group = "org.trendafilov.confucius"
version = "1.4-SNAPSHOT"
description = "confucius"

java.toolchain.languageVersion.set(JavaLanguageVersion.of(16))

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:22.0.0")
    implementation("org.slf4j:slf4j-api:1.7.5")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.6")

    testImplementation("com.google.jimfs:jimfs:1.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
