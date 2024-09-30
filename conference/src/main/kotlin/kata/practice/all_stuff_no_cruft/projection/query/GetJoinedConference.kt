package kata.practice.all_stuff_no_cruft.projection.query

data class GetJoinedConference(
    val userId: Long,
    val continuationToken: String?
)