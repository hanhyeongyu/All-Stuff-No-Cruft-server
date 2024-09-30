package kata.practice.all_stuff_no_cruft.talk

class TalkRequests {
    data class VoteUp(
        val talkId: Long
    )

    data class VoteUpCancel(
        val talkId: Long
    )

    data class VoteDown(
        val talkId: Long
    )

    data class VoteDownCancel(
        val talkId: Long
    )
}