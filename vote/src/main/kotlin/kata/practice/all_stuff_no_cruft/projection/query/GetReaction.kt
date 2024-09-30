package kata.practice.all_stuff_no_cruft.projection.query

data class GetReaction(
    val talkId: Long,
    val userId: Long?
)