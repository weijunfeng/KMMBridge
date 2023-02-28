/*
 * Copyright (c) 2022 Touchlab.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.util.*

plugins {
    `kotlin-dsl`
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.vanniktech.maven.publish.base")
    id("com.gradle.plugin-publish") version "1.0.0"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

val GROUP: String by project
val VERSION_NAME: String by project

gradlePlugin {
    plugins {
        register("faktory-kmmbridge-plugin") {
            id = "${GROUP}.kmmbridge"
            implementationClass = "co.touchlab.faktory.KMMBridgePlugin"
            displayName = "KMMBridge for Teams"
        }
    }
}

ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKeyRingFile"] = null
ext["ossrhUsername"] = null
ext["ossrhPassword"] = null
val localPropsFile = rootProject.file("local.properties")
if (localPropsFile.exists()) {
    localPropsFile.reader()
        .use { Properties().apply { load(it) } }
        .onEach { (name, value) -> ext[name.toString()] = value.toString() }
} else {
    ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
    ext["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE")
    ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
    ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
}
fun getExtraString(name: String) = ext[name]?.toString()

publishing {
    repositories {
        // 发布到本地
        mavenLocal {
            name = "local"
            url = uri("${project.buildDir}/repo")
        }
        maven {
            name = "release"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = getExtraString("ossrhUsername")
                password = getExtraString("ossrhPassword")
            }
        }
    }
}

pluginBundle {
    website = ""
    vcsUrl = ""

    description = ""

    tags = listOf("kmm", "kotlin", "multiplatform", "mobile", "ios", "xcode", "framework", "binary", "publish", "consume")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(gradleApi())
    implementation(kotlin("gradle-plugin"))
    implementation(kotlin("compiler-embeddable"))

    implementation("javax.json:javax.json-api:1.1.4")
    implementation("org.glassfish:javax.json:1.1.4")
    implementation("commons-codec:commons-codec:1.15")
    implementation("software.amazon.awssdk:s3:2.17.276")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.google.code.gson:gson:2.9.0")
    testImplementation(kotlin("test"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = GROUP
version = VERSION_NAME

mavenPublishing {
//    publishToMavenCentral()
    val releaseSigningEnabled =
        project.properties["RELEASE_SIGNING_ENABLED"]?.toString()?.equals("false", ignoreCase = true) != true
    if (releaseSigningEnabled) signAllPublications()
    pomFromGradleProperties()
}
