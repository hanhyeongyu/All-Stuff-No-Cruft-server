package kata.practice.all_stuff_no_cruft.projection.querymodel

import jakarta.persistence.EntityManager
import kata.practice.all_stuff_no_cruft.Page
import kata.practice.all_stuff_no_cruft.model.Conference
import kata.practice.all_stuff_no_cruft.projection.query.GetJoinedConference

class GetJoinedConferencesQueryProcessor(
    private val entityManager: EntityManager,
){

    companion object{
        private  const val PAGE_SIZE = 10

        private const val QUERY_STRING = """
            SELECT c
            FROM ConferenceMember cm
            JOIN Conference c ON c.id = cm.id
            WHERE cm.userId = :userId 
            AND :lastEvaluatedId IS NULL OR cm.id < :lastEvaluatedId 
            ORDER BY cm.id DESC
        """
    }

    fun process(getJoinedConference: GetJoinedConference): Page<Conference> {
        val result = fetch(getJoinedConference)
        return putOnPage(result)
    }

    private fun fetch(getJoinedConference: GetJoinedConference): List<Conference>{
        return entityManager.createQuery(QUERY_STRING, Conference::class.java)
            .setParameter("userId", getJoinedConference.userId)
            .setParameter("lastEvaluatedId", getJoinedConference.continuationToken)
            .setMaxResults(PAGE_SIZE)
            .resultList
    }


    private fun putOnPage(result: List<Conference>): Page<Conference> {
        return Page(result, lastEvaluatedId(result))
    }

    private fun lastEvaluatedId(result: List<Conference>): String?{
        if (result.count() == PAGE_SIZE){
            return result.last().id.toString()
        }else{
            return null
        }
    }
}