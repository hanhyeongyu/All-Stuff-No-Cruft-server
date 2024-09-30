package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.ReactionType
import kata.practice.all_stuff_no_cruft.model.Reaction
import kata.practice.all_stuff_no_cruft.projection.querymodel.UpDownCount

data class ReactionViewModel(
    val targetId: Long,
    val up: Long,
    val down: Long,
    var reactionType: ReactionType?
){
    constructor(
        upDownCount: UpDownCount,
        userReaction: Reaction?
    ): this(
        upDownCount.targetId,
        upDownCount.upCount,
        upDownCount.downCount,
        userReaction?.type
    )
}