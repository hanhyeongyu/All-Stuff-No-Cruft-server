package all_stuff_no_crudt.application.command

data class UnSubscribe(
    val targetType: String,
    val targetId: Long,
    val userId: Long
)