package kata.practice.all_stuff_no_cruft.respository

import kata.practice.all_stuff_no_cruft.model.Reaction
import kata.practice.all_stuff_no_cruft.model.ReactionType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ReactionRepository: JpaRepository<Reaction, Long>{

    fun findByUserIdAndTargetTypeAndTargetId(
        userId: Long,
        targetType: String,
        targetId: Long
    ): Optional<Reaction>

}