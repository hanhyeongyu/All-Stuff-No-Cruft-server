package kata.practice.all_stuff_no_cruft.member

import kata.practice.all_stuff_no_cruft.application.UserApplication
import kata.practice.all_stuff_no_cruft.application.command.Signup
import kata.practice.all_stuff_no_cruft.member.MemberSignupController.Companion.ENDPOINT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class MemberSignupController(
    private val memberApplication: UserApplication
){

    @PostMapping
    fun signup(@RequestBody command: Signup): ResponseEntity<Void>{
        memberApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "member/signup"
    }
}