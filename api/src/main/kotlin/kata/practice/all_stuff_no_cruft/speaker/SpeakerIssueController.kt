package kata.practice.all_stuff_no_cruft.speaker

import kata.practice.all_stuff_no_cruft.codable.PasswordEncodable
import kata.practice.all_stuff_no_cruft.common.exception.BaseException
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.INVALID_PASSWORD
import kata.practice.all_stuff_no_cruft.jwt.IssueToken
import kata.practice.all_stuff_no_cruft.jwt.JwtService
import kata.practice.all_stuff_no_cruft.model.Speaker
import kata.practice.all_stuff_no_cruft.speaker.SpeakerIssueController.Companion.ENDPOINT
import kata.practice.all_stuff_no_cruft.projection.SpeakerReadModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class SpeakerIssueController(
    private val jwtService: JwtService,
    private val passwordEncodable: PasswordEncodable,
    private val speakerReadModel: SpeakerReadModel,
){

    @PostMapping()
    fun issue(@RequestBody request: SpeakerRequests.IssueTokenRequest): ResponseEntity<IssueToken>{
        val member = speakerReadModel.speaker(request.email) ?: throw EntityNotFoundException()
        if (isMatch(request, member)){
            val token = jwtService.issueToken(member.id.toString(), listOf("SPEAKER"))
            return ResponseEntity.ok(token)
        }else{
            throw BaseException(INVALID_PASSWORD)
        }
    }


    @PostMapping("/refresh")
    fun issue(@RequestBody request: SpeakerRequests.RefreshToken): ResponseEntity<IssueToken>{
        val token = jwtService.issueToken(request.refreshToken)
        return ResponseEntity.ok(token)
    }

    private fun isMatch(
        request: SpeakerRequests.IssueTokenRequest,
        speaker: Speaker
    ): Boolean{
        return passwordEncodable.matches(request.password, speaker.encodedPassword.value)
    }


    companion object{
        internal const val ENDPOINT = "speaker/issue"
    }
}