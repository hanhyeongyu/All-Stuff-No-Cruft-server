package kata.practice.all_stuff_no_cruft.processor

import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.model.Reaction
import kata.practice.all_stuff_no_cruft.respository.ReactionRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UpDownProcessor(
    private val reactionRepository: ReactionRepository
){

    fun up(targetType: String, targetId: Long, userId: Long) {
        val reaction = reactionRepository.findByUserIdAndTargetTypeAndTargetId(
            userId = userId,
            targetType = targetType,
            targetId = targetId
        ).getOrNull() ?: Reaction.up(
            targetType = targetType,
            targetId = targetId,
            userId = userId
        )

        reaction.up()
        reaction.active()

        reactionRepository.save(reaction)
    }

    fun cancelUp(targetType: String, targetId: Long, userId: Long) {
        val reaction = reactionRepository.findByUserIdAndTargetTypeAndTargetId(
            userId = userId,
            targetType = targetType,
            targetId = targetId
        ).getOrNull() ?: throw EntityNotFoundException()

        reaction.delete()

        reactionRepository.save(reaction)
    }

    fun down(targetType: String, targetId: Long, userId: Long){
        val reaction = reactionRepository.findByUserIdAndTargetTypeAndTargetId(
            userId = userId,
            targetType = targetType,
            targetId = targetId
        ).getOrNull() ?: Reaction.down(
            targetType = targetType,
            targetId = targetId,
            userId = userId
        )

        reaction.down()
        reaction.active()


        reactionRepository.save(reaction)
    }

    fun cancelDown(targetType: String, targetId: Long, userId: Long){
        val reaction = reactionRepository.findByUserIdAndTargetTypeAndTargetId(
            userId = userId,
            targetType = targetType,
            targetId = targetId,
        ).getOrNull() ?: throw EntityNotFoundException()

        reaction.delete()

        reactionRepository.save(reaction)
    }
}