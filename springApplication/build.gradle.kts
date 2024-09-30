
tasks.getByName("bootJar"){
    enabled = true
}

tasks.getByName("jar"){
    enabled = false
}


dependencies{
    implementation(project(":api"))

    implementation(project(":platform"))
    implementation(project(":security"))

    implementation(project(":support:log"))
    implementation(project(":support:storage"))
    implementation(project(":support:file"))

    implementation(project(":user"))
    implementation(project(":notification"))


    implementation("org.springframework.boot:spring-boot-starter-web")
}