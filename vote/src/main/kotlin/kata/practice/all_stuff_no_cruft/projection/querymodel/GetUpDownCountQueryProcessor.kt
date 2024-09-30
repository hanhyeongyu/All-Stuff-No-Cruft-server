package kata.practice.all_stuff_no_cruft.projection.querymodel

import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import kata.practice.all_stuff_no_cruft.EntityStatus
import kata.practice.all_stuff_no_cruft.model.ReactionType
import kata.practice.all_stuff_no_cruft.projection.query.GetUpDownCount

class GetUpDownCountQueryProcessor(
    private val entityManager: EntityManager
){

    companion object{
        private const val QUERY_STRING = """
                SELECT new kata.practice.all_stuff_no_cruft.projection.querymodel.UpDownCount(
                    v.targetId, 
                    COUNT(CASE WHEN v.type = :upReaction THEN 1 END), 
                    COUNT(CASE WHEN v.type = :downReaction THEN 1 END)
                )
                FROM Reaction v 
                WHERE v.targetType = :targetType AND v.targetId = :targetId AND v.entityStatus = :entityStatus
                GROUP BY v.targetId
        """
    }


    fun process(query: GetUpDownCount): UpDownCount{
        return fetch(query)
    }

    private fun fetch(query: GetUpDownCount): UpDownCount{
        try{
            return  entityManager.createQuery(QUERY_STRING, UpDownCount::class.java)
                .setParameter("targetType", query.targetType)
                .setParameter("targetId", query.targetId)
                .setParameter("upReaction", ReactionType.UP)
                .setParameter("downReaction", ReactionType.DOWN)
                .setParameter("entityStatus", EntityStatus.ACTIVE)
                .singleResult
        }catch (e: NoResultException){
            return UpDownCount(query.targetId, 0, 0)
        }
    }
}