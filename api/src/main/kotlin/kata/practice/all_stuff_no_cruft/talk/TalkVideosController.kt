package kata.practice.all_stuff_no_cruft.talk

import kata.practice.all_stuff_no_cruft.projection.TalkReadModel
import kata.practice.all_stuff_no_cruft.projection.query.GetVideos
import kata.practice.all_stuff_no_cruft.projection.viewmodel.MediaViewModel
import kata.practice.all_stuff_no_cruft.talk.TalkVideosController.Companion.ENDPOINT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class TalkVideosController(
    private val talkReadModel: TalkReadModel
){

    @GetMapping()
    fun talkVideos(@RequestParam talkId: Long): ResponseEntity<List<MediaViewModel>>{
        val query = GetVideos(talkId)
        val result = talkReadModel.videos(query)
        return ResponseEntity.ok(result)
    }

    companion object{
        internal const val ENDPOINT = "talkVideos"
    }
}