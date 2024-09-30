package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable


@Embeddable
data class Information(
    @Column(length = 1000)
    val content: String,
    val contentType: String
)