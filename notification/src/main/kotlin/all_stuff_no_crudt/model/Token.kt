package all_stuff_no_crudt.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.all_stuff_no_cruft.BaseEntity
import java.time.LocalDateTime


@Entity
class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var value: String,
    val userId: Long,
    val timeStamp: LocalDateTime,
    val platform: Platform
): BaseEntity(){


    companion object {
        fun create(value: String, userId: Long, platform: Platform): Token{
            return Token(
                value = value,
                userId = userId,
                timeStamp = LocalDateTime.now(),
                platform = platform
            )
        }
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Token

        if (value != other.value) return false

        return true
    }
}