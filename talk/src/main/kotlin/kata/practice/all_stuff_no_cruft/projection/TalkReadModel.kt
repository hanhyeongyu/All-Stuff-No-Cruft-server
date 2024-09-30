package kata.practice.all_stuff_no_cruft.projection

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import kata.practice.all_stuff_no_cruft.application.TalkApplication
import kata.practice.all_stuff_no_cruft.application.TalkApplication.Companion.TALK_VOTE_TARGET_TYPE
import kata.practice.all_stuff_no_cruft.model.*
import kata.practice.all_stuff_no_cruft.projection.query.*
import kata.practice.all_stuff_no_cruft.projection.querymodel.GetTalksQueryProcessor
import kata.practice.all_stuff_no_cruft.projection.querymodel.GetUpDownCountQueryProcessor
import kata.practice.all_stuff_no_cruft.projection.querymodel.GetUserReactionQueryProcessor
import kata.practice.all_stuff_no_cruft.projection.viewmodel.*
import kata.practice.all_stuff_no_cruft.service.ProfileImageService
import kata.practice.all_stuff_no_cruft.service.SlideService
import kata.practice.all_stuff_no_cruft.service.MediaService
import org.springframework.stereotype.Service

@Service
class TalkReadModel(
    private val mediaService: MediaService,
    private val slideService: SlideService,
    private val profileImageService: ProfileImageService
){

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun talks(getTalks: GetTalks): List<TalkViewModel>{
        val queryProcessor = GetTalksQueryProcessor(entityManager)
        val result = queryProcessor.process(getTalks).map { talkWithSpeaker ->
            val view = TalkViewModel(talkWithSpeaker)

            val speaker = talkWithSpeaker.speaker
            view.speaker.imageURL = profileUrl(speaker)

            return@map view
        }
        return result
    }

    fun videos(getVideos: GetVideos): List<MediaViewModel>{
        val result = mediaService.medias(getVideos.talkId)
            .map { media ->
                val view = MediaViewModel(media)
                view.videoURL = talkVideoUrl(media)
                return@map view
            }

        return  result
    }

    fun slides(getSlides: GetSlides): List<SlideViewModel>{
        //TODO: Check authority
        val result = slideService.slide(getSlides.talkId)
            .map { slide ->
                val view = SlideViewModel(slide)
                view.slideURL = slideUrl(slide)
                return@map view
            }


        return result
    }

    fun reaction(getReaction: GetReaction): ReactionViewModel{
        val upDownQueryProcessor = GetUpDownCountQueryProcessor(entityManager)
        val upDownQuery = GetUpDownCount(
            targetType = TALK_VOTE_TARGET_TYPE,
            targetId = getReaction.talkId
        )

        val countDown = upDownQueryProcessor.process(upDownQuery)
        val userReaction = getReaction.userId?.let { userId ->
            val queryProcessor = GetUserReactionQueryProcessor(entityManager)
            val userReactionQuery = GetUserReaction(
                TALK_VOTE_TARGET_TYPE,
                getReaction.talkId,
                userId
            )
            queryProcessor.process(userReactionQuery)
        }

        return ReactionViewModel(countDown, userReaction)
    }



    private fun profileUrl(speaker: Speaker): String?{
        return profileImageService.url(speaker.id!!)
    }

    private fun slideUrl(slide: Slide): String?{
        return slideService.url(slide.id!!)
    }

    private fun talkVideoUrl(media: Media): String?{
        return mediaService.url(media.mediaId)
    }


}