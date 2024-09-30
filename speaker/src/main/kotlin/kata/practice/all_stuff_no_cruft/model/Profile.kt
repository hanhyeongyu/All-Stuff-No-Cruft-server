package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Embeddable
import java.nio.file.Paths

@Embeddable
data class Profile(
    val name: String,

    val introduce: String,
)