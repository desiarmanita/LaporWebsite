plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.12.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.9.3")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.29.0")
    testImplementation("org.seleniumhq.selenium:selenium-devtools-v130:4.26.0")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:4.29.0")
    testImplementation("org.testng:testng:7.8.0")
    //implementation("org.seleniumhq.selenium:selenium-java:4.10.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.9.1")

}

tasks.test {
    useTestNG()
}