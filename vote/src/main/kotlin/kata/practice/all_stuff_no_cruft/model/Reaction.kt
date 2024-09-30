package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.*
import kata.practice.all_stuff_no_cruft.BaseEntity
import kata.practice.all_stuff_no_cruft.EntityStatus
import kata.practice.all_stuff_no_cruft.EntityStatus.ACTIVE
import kata.practice.all_stuff_no_cruft.EntityStatus.DELETE
import kata.practice.all_stuff_no_cruft.model.ReactionType.UP
import kata.practice.all_stuff_no_cruft.model.ReactionType.DOWN


@Entity
class Reaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val targetType: String,

    val targetId: Long,

    val userId: Long,

    @Enumerated(EnumType.STRING)
    var type: ReactionType,

    @Enumerated(EnumType.STRING)
    var entityStatus: EntityStatus
): BaseEntity(){

    companion object{
        fun up(targetType: String, targetId: Long, userId: Long): Reaction{
            return Reaction(
                targetType = targetType,
                targetId = targetId,
                userId = userId,
                type = UP,
                entityStatus = ACTIVE
            )
        }

        fun down(targetType: String, targetId: Long, userId: Long): Reaction{
            return Reaction(
                targetType = targetType,
                targetId = targetId,
                userId = userId,
                type = DOWN,
                entityStatus = ACTIVE
            )
        }
    }

    fun active(){
        entityStatus = ACTIVE
    }

    fun delete(){
        entityStatus = DELETE
    }

    fun up(){
        type = UP
    }

    fun down(){
        type = DOWN
    }
}