package all_stuff_no_crudt.application.command

import all_stuff_no_crudt.model.Platform

data class OnNewToken(
    val token: String,
    val userId: Long,
    val platform: Platform
)