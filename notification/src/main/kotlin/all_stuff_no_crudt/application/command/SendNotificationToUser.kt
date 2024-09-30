package all_stuff_no_crudt.application.command

data class SendNotificationToUser(
    val title: String,
    val body: String,
    val userId: Long
)


