package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.*
import kata.practice.all_stuff_no_cruft.BaseEntity
import java.nio.file.Path
import java.nio.file.Paths

@Entity
class Poster(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val conferenceId: Long,

    val filePath: String
): BaseEntity()