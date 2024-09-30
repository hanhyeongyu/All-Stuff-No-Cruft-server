package kata.practice.all_stuff_no_cruft.conference


import kata.practice.all_stuff_no_cruft.Page
import kata.practice.all_stuff_no_cruft.application.ConferenceApplication
import kata.practice.all_stuff_no_cruft.application.command.JoinConference
import kata.practice.all_stuff_no_cruft.argumentResolver.UserId
import kata.practice.all_stuff_no_cruft.conference.ConferenceJoinController.Companion.ENDPOINT
import kata.practice.all_stuff_no_cruft.projection.ConferenceReadModel
import kata.practice.all_stuff_no_cruft.projection.query.GetJoinedConference
import kata.practice.all_stuff_no_cruft.projection.viewmodel.ConferenceViewModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ENDPOINT)
class ConferenceJoinController(
    private val conferenceApplication: ConferenceApplication,
    private val conferenceReadModel: ConferenceReadModel
){

    @PostMapping
    fun join(
        @UserId userId: Long,
        @RequestBody request: ConferenceRequests.JoinConference,
    ): ResponseEntity<Void>{
        val command = JoinConference(
            userId = userId,
            conferenceId = request.conferenceId
        )
        conferenceApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun joinedConference(
        @UserId userId: Long,
        @RequestParam continuationToken: String?
    ): ResponseEntity<Page<ConferenceViewModel>>{
        val query = GetJoinedConference(userId, continuationToken)
        val result = conferenceReadModel.conferences(query)
        return ResponseEntity.ok(result)
    }

    companion object{
        internal const val ENDPOINT = "conferences/join"
    }
}