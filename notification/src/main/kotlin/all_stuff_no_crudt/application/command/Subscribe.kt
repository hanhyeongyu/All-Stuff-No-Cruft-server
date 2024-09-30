package all_stuff_no_crudt.application.command

data class Subscribe(
    val targetType: String,
    val targetId: Long,
    val userId: Long
)