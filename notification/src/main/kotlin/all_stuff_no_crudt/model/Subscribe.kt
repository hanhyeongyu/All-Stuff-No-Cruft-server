package all_stuff_no_crudt.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.all_stuff_no_cruft.BaseEntity
import kata.practice.all_stuff_no_cruft.EntityStatus
import kata.practice.all_stuff_no_cruft.EntityStatus.ACTIVE


@Entity
class Subscribe(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    val targetType: String,

    val targetId: Long,

    val userId: Long,

    var entityStatus: EntityStatus
): BaseEntity(){

    fun topic(): Topic{
        return Topic(targetType, targetId)
    }

    companion object{
        fun create(targetType: String, targetId: Long, userId: Long): Subscribe{
            return Subscribe(
                targetType = targetType,
                targetId = targetId,
                userId = userId,
                entityStatus = ACTIVE
            )
        }
    }
}