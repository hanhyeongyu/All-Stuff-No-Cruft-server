package kata.practice.all_stuff_no_cruft.application

import kata.practice.all_stuff_no_cruft.application.command.AddSlides
import kata.practice.all_stuff_no_cruft.application.command.AddTalk
import kata.practice.all_stuff_no_cruft.application.command.AddMedia
import kata.practice.all_stuff_no_cruft.application.command.vote.CancelDown
import kata.practice.all_stuff_no_cruft.application.command.vote.CancelUp
import kata.practice.all_stuff_no_cruft.application.command.vote.Down
import kata.practice.all_stuff_no_cruft.application.command.vote.Up
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.common.exception.IllegalStatusException
import kata.practice.all_stuff_no_cruft.model.Talk
import kata.practice.all_stuff_no_cruft.processor.UpDownProcessor
import kata.practice.all_stuff_no_cruft.repository.ConferenceRepository
import kata.practice.all_stuff_no_cruft.repository.SpeakerRepository
import kata.practice.all_stuff_no_cruft.repository.TalkRepository
import kata.practice.all_stuff_no_cruft.service.SlideService
import kata.practice.all_stuff_no_cruft.service.MediaService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class TalkApplication(
    private val talkRepository: TalkRepository,
    private val conferenceRepository: ConferenceRepository,
    private val speakerRepository: SpeakerRepository,
    private val slideService: SlideService,
    private val mediaService: MediaService,
    private val upDownProcessor: UpDownProcessor
){

    /**
     * Factory
     */
    @Transactional
    fun handle(command: AddTalk){
        val conference = conferenceRepository.findById(command.conferenceId)
            .getOrNull() ?: throw EntityNotFoundException()

        val speaker = speakerRepository.findById(command.speakerId)
            .getOrNull() ?: throw EntityNotFoundException()


        val talk = Talk(
            conferenceId = conference.id!!,
            speakerId = speaker.id!!,
            name = command.name,
            description = command.description
        )
        talkRepository.save(talk)
    }

    /**
     * Resources
     */

    @Transactional
    fun handle(command: AddSlides){
        val talk = talkRepository.findById(command.talkId)
            .getOrNull() ?: throw EntityNotFoundException()

        val uploader = speakerRepository.findById(command.uploaderId)
            .getOrNull() ?: throw EntityNotFoundException()

        if (!talk.hasAuthority(uploader)){
            throw throw IllegalStatusException("Uploader hasn't authority")
        }

        command.slides.forEach { pdf ->
            val talkId = talk.id!!
            slideService.save(talkId, pdf)
        }
    }

    @Transactional
    fun handle(command: AddMedia){
        val talk = talkRepository.findById(command.talkId)
            .getOrNull() ?: throw EntityNotFoundException()

        val mediaId = createMediaId()
        val talkId = talk.id!!
        val video = command.video

        mediaService.save(
            mediaId = mediaId,
            talkId = talkId,
            video = video
        )
        mediaService.process(mediaId)
    }


    /**
     * Vote - Up, down
     */

    @Transactional
    fun handle(command: Up){
        upDownProcessor.up(TALK_VOTE_TARGET_TYPE, command.talkId, command.userId)
    }

    @Transactional
    fun handle(command: CancelUp){
        upDownProcessor.cancelUp(TALK_VOTE_TARGET_TYPE, command.talkId, command.userId)
    }

    @Transactional
    fun handle(command: Down){
        upDownProcessor.down(TALK_VOTE_TARGET_TYPE, command.talkId, command.userId)
    }

    @Transactional
    fun handle(command: CancelDown){
        upDownProcessor.cancelDown(TALK_VOTE_TARGET_TYPE, command.talkId, command.userId)
    }


    private fun createMediaId(): UUID{
        return UUID.randomUUID()
    }


    companion object{
        internal const val TALK_VOTE_TARGET_TYPE = "talks"
    }
}