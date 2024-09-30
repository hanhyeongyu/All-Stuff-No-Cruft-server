package kata.practice.all_stuff_no_cruft.projection

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import kata.practice.all_stuff_no_cruft.Page
import kata.practice.all_stuff_no_cruft.model.Conference
import kata.practice.all_stuff_no_cruft.projection.query.GetConferences
import kata.practice.all_stuff_no_cruft.projection.query.GetJoinedConference
import kata.practice.all_stuff_no_cruft.projection.querymodel.GetConferencesQueryProcessor
import kata.practice.all_stuff_no_cruft.projection.querymodel.GetJoinedConferencesQueryProcessor
import kata.practice.all_stuff_no_cruft.projection.viewmodel.ConferenceViewModel
import kata.practice.all_stuff_no_cruft.service.PosterService
import org.springframework.stereotype.Service


@Service
class ConferenceReadModel(
    private val posterService: PosterService
){

    @PersistenceContext
    private lateinit var entityManager: EntityManager


    fun conferences(query: GetConferences): Page<ConferenceViewModel> {
        val queryProcessor = GetConferencesQueryProcessor(entityManager)

        val result = queryProcessor.process(query).map { conference ->
            val view = ConferenceViewModel(conference)
            view.posterURL = posterUrl(conference)
            return@map view
        }
        return result
    }

    fun conferences(query: GetJoinedConference): Page<ConferenceViewModel>{
        val queryProcessor = GetJoinedConferencesQueryProcessor(entityManager)

        val result = queryProcessor.process(query).map { conference ->
            val view = ConferenceViewModel(conference)
            view.posterURL = posterUrl(conference)
            return@map view
        }
        return result
    }


    private fun posterUrl(conference: Conference): String?{
        return posterService.url(conference.id!!)
    }

}