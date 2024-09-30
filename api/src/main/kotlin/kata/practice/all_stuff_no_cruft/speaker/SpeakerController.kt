package kata.practice.all_stuff_no_cruft.speaker

import kata.practice.all_stuff_no_cruft.application.SpeakerApplication
import kata.practice.all_stuff_no_cruft.application.command.UpdateProfileImage
import kata.practice.all_stuff_no_cruft.projection.SpeakerReadModel
import kata.practice.all_stuff_no_cruft.projection.viewmodel.SpeakerViewModel
import kata.practice.all_stuff_no_cruft.speaker.SpeakerController.Companion.ENDPOINT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.security.Principal

@RestController
@RequestMapping(ENDPOINT)
class SpeakerController(
    private val speakerApplication: SpeakerApplication,
    private val speakerReadModel: SpeakerReadModel
){

    @GetMapping()
    fun speaker(principal: Principal): ResponseEntity<SpeakerViewModel> {
        val result = speakerReadModel.profile(principal)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/profileImage")
    fun updateProfileImage(
        principal: Principal,
        @RequestPart image: MultipartFile,
    ): ResponseEntity<Void>{
        val command = UpdateProfileImage(principal, image)
        speakerApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "speakers"
    }
}