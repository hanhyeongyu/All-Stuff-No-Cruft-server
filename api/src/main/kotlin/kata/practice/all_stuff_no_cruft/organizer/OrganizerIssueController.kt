package kata.practice.all_stuff_no_cruft.organizer

import kata.practice.all_stuff_no_cruft.projection.UserReadModel
import kata.practice.all_stuff_no_cruft.codable.PasswordEncodable
import kata.practice.all_stuff_no_cruft.common.exception.BaseException
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.INVALID_PASSWORD
import kata.practice.all_stuff_no_cruft.jwt.IssueToken
import kata.practice.all_stuff_no_cruft.jwt.JwtService
import kata.practice.all_stuff_no_cruft.organizer.OrganizerIssueController.Companion.ENDPOINT
import kata.practice.all_stuff_no_cruft.model.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class OrganizerIssueController(
    private val jwtService: JwtService,
    private val passwordEncodable: PasswordEncodable,
    private val userReadModel: UserReadModel,
){

    @PostMapping()
    fun issue(@RequestBody request: OrganizerRequests.IssueTokenRequest): ResponseEntity<IssueToken>{
        val member = userReadModel.user(request.email) ?: throw EntityNotFoundException()
        if (isMatch(request, member)){
            val token = jwtService.issueToken(member.id.toString(), listOf("Organizer"))
            return ResponseEntity.ok(token)
        }else{
            throw BaseException(INVALID_PASSWORD)
        }
    }


    @PostMapping("/refresh")
    fun issue(@RequestBody request: OrganizerRequests.RefreshToken): ResponseEntity<IssueToken>{
        val token = jwtService.issueToken(request.refreshToken)
        return ResponseEntity.ok(token)
    }

    private fun isMatch(
        request: OrganizerRequests.IssueTokenRequest,
        user: User
    ): Boolean{
        return passwordEncodable.matches(request.password, user.encodedPassword.value)
    }


    companion object{
        internal const val ENDPOINT = "organizer/issue"
    }
}