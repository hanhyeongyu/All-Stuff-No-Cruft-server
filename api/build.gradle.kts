dependencies{
    implementation(project(":platform"))
    implementation(project(":security"))

    implementation(project(":user"))
    implementation(project(":speaker"))
    implementation(project(":conference"))
    implementation(project(":talk"))
    implementation(project(":vote"))
    implementation(project(":notification"))

    implementation(project(":support:file"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}