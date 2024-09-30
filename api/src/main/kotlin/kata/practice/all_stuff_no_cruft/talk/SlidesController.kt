package kata.practice.all_stuff_no_cruft.talk

import kata.practice.all_stuff_no_cruft.connector.FileConnector
import kata.practice.all_stuff_no_cruft.projection.TalkReadModel
import kata.practice.all_stuff_no_cruft.projection.query.GetSlides
import kata.practice.all_stuff_no_cruft.projection.viewmodel.SlideViewModel
import kata.practice.all_stuff_no_cruft.talk.SlidesController.Companion.ENDPOINT
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files

@RestController
@RequestMapping(ENDPOINT)
class SlidesController(
    private val talkReadModel: TalkReadModel,
    private val fileConnector: FileConnector
){

    @GetMapping
    fun slides(
        @RequestParam talkId: Long
    ): ResponseEntity<List<SlideViewModel>>{
        val query = GetSlides(talkId)
        val result = talkReadModel.slides(query)
        return ResponseEntity.ok(result)
    }

//    @GetMapping("{path}")
//    fun display(@PathVariable path: String): ResponseEntity<Resource> {
//        val resource = fileConnector.resource("slides/" + path)
//        val contentDisposition = "inline"
//        return ResponseEntity
//            .ok()
//            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
//            .contentType(MediaType.APPLICATION_PDF)
//            .body(resource)
//    }

    companion object{
        internal const val ENDPOINT = "slides"
    }
}