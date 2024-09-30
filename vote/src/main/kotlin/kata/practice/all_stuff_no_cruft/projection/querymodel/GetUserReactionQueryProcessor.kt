package kata.practice.all_stuff_no_cruft.projection.querymodel

import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import kata.practice.all_stuff_no_cruft.EntityStatus
import kata.practice.all_stuff_no_cruft.model.Reaction
import kata.practice.all_stuff_no_cruft.projection.query.GetUserReaction

class GetUserReactionQueryProcessor(
    private val entityManager: EntityManager
){

    companion object{
        private const val QUERY_STRING = """
            SELECT v
            FROM Reaction v
            WHERE v.userId = :userId 
            AND v.targetType = :targetType 
            AND v.targetId = :targetId 
            AND v.entityStatus = :entityStatus 
        """
    }

    fun process(query: GetUserReaction): Reaction?{
        return fetch(query)
    }

    private fun fetch(query: GetUserReaction): Reaction?{
        try{
            return entityManager.createQuery(QUERY_STRING, Reaction::class.java)
                .setParameter("targetType", query.targetType)
                .setParameter("targetId", query.targetId)
                .setParameter("userId", query.userId)
                .setParameter("entityStatus", EntityStatus.ACTIVE)
                .singleResult
        }catch (e: NoResultException){
            return null
        }


    }
}