package all_stuff_no_crudt.repository

import all_stuff_no_crudt.model.Subscribe
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SubscribeRepository: JpaRepository<Subscribe, Long>{
    fun findByUserId(userId : Long) : List<Subscribe>
    fun findByUserIdAndTargetTypeAndTargetId(userId: Long, targetType: String, targetId: Long): Optional<Subscribe>
}