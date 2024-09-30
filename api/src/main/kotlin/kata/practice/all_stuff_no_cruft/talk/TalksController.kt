package kata.practice.all_stuff_no_cruft.talk

import kata.practice.all_stuff_no_cruft.application.TalkApplication
import kata.practice.all_stuff_no_cruft.application.command.*
import kata.practice.all_stuff_no_cruft.argumentResolver.UserId
import kata.practice.all_stuff_no_cruft.projection.TalkReadModel
import kata.practice.all_stuff_no_cruft.projection.query.GetTalks
import kata.practice.all_stuff_no_cruft.projection.viewmodel.TalkViewModel
import kata.practice.all_stuff_no_cruft.talk.TalksController.Companion.ENDPOINT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(ENDPOINT)
class TalksController(
    private val talkApplication: TalkApplication,
    private val talkReadModel: TalkReadModel
){


    @PostMapping
    fun add(@RequestBody addTalk: AddTalk): ResponseEntity<Void>{
        talkApplication.handle(addTalk)
        return ResponseEntity.ok().build()
    }


    @GetMapping
    fun talks(@RequestParam conferenceId: Long): ResponseEntity<List<TalkViewModel>>{
        val getTalks = GetTalks(conferenceId)
        val result = talkReadModel.talks(getTalks)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/slides")
    fun addSlide(
        @RequestPart slides: List<MultipartFile>,
        @RequestPart talkId: Long,
        @UserId userId: Long
    ): ResponseEntity<Void>{
        val command = AddSlides(
            talkId = talkId,
            uploaderId = userId,
            slides = slides
        )
        talkApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/videos")
    fun updateVideo(
        @RequestPart video: MultipartFile,
        @RequestPart talkId: Long
    ): ResponseEntity<Void>{
        val command = AddMedia(
            talkId = talkId,
            video = video
        )
        talkApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    companion object{
        internal const val ENDPOINT = "/talks"
    }
}