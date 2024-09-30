package kata.practice.all_stuff_no_cruft.projection.querymodel

import jakarta.persistence.EntityManager
import kata.practice.all_stuff_no_cruft.projection.query.GetTalks
import kata.practice.all_stuff_no_cruft.projection.viewmodel.TalkViewModel

class GetTalksQueryProcessor(
    private val entityManager: EntityManager
) {

    companion object{
        internal const val QUERY_STRING = """
            SELECT new kata.practice.all_stuff_no_cruft.projection.querymodel.TalkWithSpeaker(t, s)
            From Talk t
            JOIN Speaker s ON s.id = t.speakerId
            WHERE t.conferenceId = :conferenceId
        """
    }

    fun process(getTalks: GetTalks): List<TalkWithSpeaker>{
        return fetch(getTalks)
    }


    private fun fetch(getTalks: GetTalks): List<TalkWithSpeaker>{
        return entityManager.createQuery(QUERY_STRING, TalkWithSpeaker::class.java)
            .setParameter("conferenceId", getTalks.conferenceId)
            .resultList
    }
}