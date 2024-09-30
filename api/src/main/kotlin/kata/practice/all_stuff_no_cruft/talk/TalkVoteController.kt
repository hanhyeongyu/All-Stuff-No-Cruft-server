package kata.practice.all_stuff_no_cruft.talk

import kata.practice.all_stuff_no_cruft.application.TalkApplication
import kata.practice.all_stuff_no_cruft.application.command.vote.CancelDown
import kata.practice.all_stuff_no_cruft.application.command.vote.CancelUp
import kata.practice.all_stuff_no_cruft.application.command.vote.Down
import kata.practice.all_stuff_no_cruft.application.command.vote.Up
import kata.practice.all_stuff_no_cruft.argumentResolver.UserId
import kata.practice.all_stuff_no_cruft.projection.TalkReadModel
import kata.practice.all_stuff_no_cruft.projection.query.GetReaction
import kata.practice.all_stuff_no_cruft.projection.viewmodel.ReactionViewModel
import kata.practice.all_stuff_no_cruft.talk.TalkVoteController.Companion.ENDPOINT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(ENDPOINT)
class TalkVoteController(
    private val talkApplication: TalkApplication,
    private val talkReadModel: TalkReadModel
){

    @PostMapping("/up")
    fun up(
        @RequestBody request: TalkRequests.VoteUp,
        @UserId userId: Long
    ): ResponseEntity<Void> {
        val command = Up(
            talkId = request.talkId,
            userId = userId
        )
        talkApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/up")
    fun cancelUp(
        @RequestParam talkId: Long,
        @UserId userId: Long
    ): ResponseEntity<Void> {
        val command = CancelUp(
            talkId = talkId,
            userId = userId
        )
        talkApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/down")
    fun down(
        @RequestBody request: TalkRequests.VoteDown,
        @UserId userId: Long
    ): ResponseEntity<Void> {
        val command = Down(
            talkId = request.talkId,
            userId = userId
        )
        talkApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/down")
    fun cancelDown(
        @RequestParam talkId: Long,
        @UserId userId: Long
    ): ResponseEntity<Void>{
        val command = CancelDown(
            talkId = talkId,
            userId = userId
        )
        talkApplication.handle(command)
        return ResponseEntity.ok().build()
    }


    @GetMapping("/reaction")
    fun reaction(
        @RequestParam talkId: Long,
        @UserId userId: Long
    ): ResponseEntity<ReactionViewModel> {
        val query = GetReaction(talkId, userId)
        val result = talkReadModel.reaction(query)
        return ResponseEntity.ok(result)
    }


    companion object{
        internal const val ENDPOINT = "talks/vote"
    }
}