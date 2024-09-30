package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.*
import kata.practice.all_stuff_no_cruft.BaseEntity

@Entity
class Slide(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val talkId: Long,

    val name: String?,

    val filePath: String
): BaseEntity()
