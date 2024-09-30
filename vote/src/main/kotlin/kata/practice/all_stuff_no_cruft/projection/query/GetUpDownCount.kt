package kata.practice.all_stuff_no_cruft.projection.query

data class GetUpDownCount(
    val targetType: String,
    val targetId: Long,
)