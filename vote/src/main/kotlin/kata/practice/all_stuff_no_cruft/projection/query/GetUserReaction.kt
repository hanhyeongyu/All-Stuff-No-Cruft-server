package kata.practice.all_stuff_no_cruft.projection.query

data class GetUserReaction(
    val targetType: String,
    val targetId: Long,
    val userId: Long
)