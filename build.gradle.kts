plugins {
    id("java")
}
   configurations{
       compileOnly{
           extendsFrom(configurations.annotationProcessor.get())
       }
   }

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    compileOnly("org.projectLombok:Lombok")

    annotationProcessor("org.projectLombok:Lombok:1.18.36")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}