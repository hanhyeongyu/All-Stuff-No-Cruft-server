package kata.practice.all_stuff_no_cruft.conference

import kata.practice.all_stuff_no_cruft.Page
import kata.practice.all_stuff_no_cruft.application.ConferenceApplication
import kata.practice.all_stuff_no_cruft.application.command.*
import kata.practice.all_stuff_no_cruft.conference.ConferencesController.Companion.ENDPOINT
import kata.practice.all_stuff_no_cruft.projection.ConferenceReadModel
import kata.practice.all_stuff_no_cruft.projection.query.GetConferences
import kata.practice.all_stuff_no_cruft.projection.viewmodel.ConferenceViewModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(ENDPOINT)
class ConferencesController(
    private val conferenceApplication: ConferenceApplication,
    private val conferenceReadModel: ConferenceReadModel
){

    @PostMapping
    fun add(@RequestBody command: AddConference): ResponseEntity<Void>{
        conferenceApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun conferences(@RequestParam continuationToken: String?): ResponseEntity<Page<ConferenceViewModel>>{
        val query = GetConferences(continuationToken)
        val result = conferenceReadModel.conferences(query)
        return ResponseEntity.ok().body(result)
    }

    @PostMapping("/information")
    fun updateInformation(@RequestBody command: UpdateInformation): ResponseEntity<Void>{
        conferenceApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/location")
    fun updateLocation(@RequestBody command: UpdateLocation): ResponseEntity<Void>{
        conferenceApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/prices")
    fun updatePrice(@RequestBody command: UpdatePrice): ResponseEntity<Void>{
        conferenceApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/capacity")
    fun updateCapacity(@RequestBody command: UpdateCapacity): ResponseEntity<Void>{
        conferenceApplication.handle(command)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/poster")
    fun updatePoster(
        @RequestPart image: MultipartFile,
        @RequestPart conferenceId: Long
    ): ResponseEntity<Void>{
        val command = UpdatePoster(conferenceId, image)
        conferenceApplication.handle(command)
        return ResponseEntity.ok().build()
    }


    companion object{
        internal const val ENDPOINT = "conferences"
    }
}