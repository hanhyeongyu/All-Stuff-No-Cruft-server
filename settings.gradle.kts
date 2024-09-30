rootProject.name = "all_stuff_no_cruft"



include(
    "springApplication",

    "api",

    "platform",

    "support:log",
    "support:storage",
    "support:file",

    "security",

    "user",
    "conference",
    "talk",
    "speaker",
    "vote",
    "notification",
)

pluginManagement{
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings


    resolutionStrategy {
        eachPlugin {
            when(requested.id.id){
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion) }
        }
    }

}