package kata.practice.all_stuff_no_cruft.speaker

import kata.practice.all_stuff_no_cruft.application.SpeakerApplication
import kata.practice.all_stuff_no_cruft.application.command.SignupSpeaker
import kata.practice.all_stuff_no_cruft.speaker.SpeakerSignupController.Companion.ENDPOINT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class SpeakerSignupController(
    private val speakerApplication: SpeakerApplication
){

    @PostMapping
    fun signup(@RequestBody command: SignupSpeaker): ResponseEntity<Void>{
        speakerApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "speaker/signup"
    }
}