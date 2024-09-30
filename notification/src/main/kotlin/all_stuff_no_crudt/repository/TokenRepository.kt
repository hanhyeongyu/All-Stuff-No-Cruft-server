package all_stuff_no_crudt.repository

import all_stuff_no_crudt.model.Token
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TokenRepository: JpaRepository<Token, Long>{
    fun findByValue(value: String): Optional<Token>
    fun findAllByUserId(userId: Long): List<Token>
}