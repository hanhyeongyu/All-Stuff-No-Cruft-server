dependencies{
    implementation(project(":platform"))
    implementation(project(":security"))

    implementation(project(":support:file"))

    compileOnly("org.springframework:spring-web")
    compileOnly("org.springframework:spring-context")
    compileOnly("org.springframework:spring-tx")
    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
}