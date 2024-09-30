package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ProfileImage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?  = null,

    val userId: Long,

    val filePath: String
)