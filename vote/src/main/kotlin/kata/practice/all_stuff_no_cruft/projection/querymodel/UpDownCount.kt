package kata.practice.all_stuff_no_cruft.projection.querymodel

data class UpDownCount(
    val targetId: Long,
    val upCount: Long,
    val downCount: Long
)