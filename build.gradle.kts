import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.ferox"
version = "1.0"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://repo.fusesource.com/nexus/content/repositories/releases-3rd-party")
    maven("https://repo.runelite.net")
}

plugins {
    id("java")
    kotlin("jvm") version "1.3.72"
    application
}


dependencies {

    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("com.github.Graviton1647:reflections:0.9.17")
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.imgscalr:imgscalr-lib:4.2")
    implementation("io.github.microutils:kotlin-logging:1.12.0")
    implementation("ch.qos.logback:logback-classic:1.2.5")
    implementation("it.unimi.dsi:fastutil:8.5.4")
    implementation("com.github.Graviton1647:reflections:0.9.17")
    implementation("net.runelite.pushingpixels:substance:8.0.02")
    implementation("net.runelite.pushingpixels:trident:1.5.00")
    implementation("net.java.dev.jna:jna:5.3.1")

    implementation("net.runelite:discord:1.4")
    implementation("net.sf.jopt-simple:jopt-simple:5.0.1")
    implementation("com.google.guava:guava:23.2-jre")
    implementation("com.google.code.findbugs:jsr305:1.3.9")
    implementation("com.google.errorprone:error_prone_annotations:2.0.18")
    implementation("com.google.j2objc:j2objc-annotations:1.1")
    implementation("org.codehaus.mojo:animal-sniffer-annotations:1.14")
    implementation("com.google.inject:guice:4.0")
    implementation("com.apple:AppleJavaExtensions:1.4")
    implementation("org.apache.commons:commons-text:1.2")
    implementation("org.apache.commons:commons-lang3:3.7")
    implementation("javax.inject:javax.inject:1")
    implementation("aopalliance:aopalliance:1.0")
    implementation("org.apache.commons:commons-text:1.2")
    implementation("org.apache.commons:commons-lang3:3.7")
    implementation("com.squareup.okhttp3:okhttp:3.7.0") {
        exclude("okhttp3")
    }
    implementation("net.java.dev.jna:jna-platform:5.8.0")
    implementation("org.projectlombok:lombok:1.18.18")
    annotationProcessor("org.projectlombok:lombok:1.18.18")

    implementation("io.sentry:sentry:5.1.2")
    implementation("commons-io:commons-io:2.8.0")
    implementation("com.squareup.okio:okio:1.12.0")
    implementation("net.java.dev.jna:jna:5.3.1")
    implementation("com.joyent.util:fast-md5:2.7.1")

    implementation("net.runelite.jogl:jogl-all:2.4.0-rc-20200429")
    implementation("net.runelite.jocl:jocl:1.0")
    implementation("net.runelite.gluegen:gluegen-rt:2.4.0-rc-20200429")

    implementation("net.runelite.gluegen:gluegen-rt:2.4.0-rc-20200429:natives-windows-amd64")
    implementation("net.runelite.gluegen:gluegen-rt:2.4.0-rc-20200429:natives-windows-i586")
    implementation("net.runelite.gluegen:gluegen-rt:2.4.0-rc-20200429:natives-linux-amd64")

    implementation("net.runelite.jogl:jogl-all:2.4.0-rc-20200429:natives-windows-amd64")
    implementation("net.runelite.jogl:jogl-all:2.4.0-rc-20200429:natives-windows-i586")
    implementation("net.runelite.jogl:jogl-all:2.4.0-rc-20200429:natives-linux-amd64")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val output = File("/release")

val jarClient = tasks.register("fatJar", Jar::class.java) {
    archiveClassifier.set("all")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "net.runelite.client.RuneLite"
    }
    destinationDir = file(output)
    this.baseName = "Client"
    val sourcesMain = sourceSets.main.get()
    //from(sourcesMain.output)
    from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks.jar.get() as CopySpec)
}

application {
    mainClass.set("com.ferox.Client")
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
