package kata.practice.all_stuff_no_cruft.projection.querymodel

import jakarta.persistence.EntityManager
import kata.practice.all_stuff_no_cruft.Page
import kata.practice.all_stuff_no_cruft.model.Conference
import kata.practice.all_stuff_no_cruft.projection.query.GetConferences

class GetConferencesQueryProcessor(
    private val entityManager: EntityManager
){

    companion object{
        private  const val PAGE_SIZE = 10

        private const val QUERY_STRING = """
            SELECT c
            FROM Conference c
            WHERE (:lastEvaluatedId IS NULL OR c.id < :lastEvaluatedId) 
            ORDER BY c.id DESC
        """
    }

    fun process(getConferences: GetConferences): Page<Conference> {
        val result = fetch(getConferences)
        return putOnPage(result)
    }

    private fun fetch(getConferences: GetConferences): List<Conference>{
        return entityManager.createQuery(QUERY_STRING, Conference::class.java)
            .setParameter("lastEvaluatedId", getConferences.continuationToken)
            .setMaxResults(PAGE_SIZE)
            .resultList
    }


    private fun putOnPage(result: List<Conference>): Page<Conference>{
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