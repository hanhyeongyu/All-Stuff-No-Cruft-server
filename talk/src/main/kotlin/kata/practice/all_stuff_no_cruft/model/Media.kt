package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.all_stuff_no_cruft.BaseEntity
import java.util.UUID

@Entity
class Media(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val mediaId: UUID,

    val talkId: Long,

    val name: String?,

    val filePath: String
): BaseEntity()