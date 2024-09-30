package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.all_stuff_no_cruft.BaseEntity

@Entity
class ConferenceMember(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val conferenceId: Long,

    val userId: Long
): BaseEntity()