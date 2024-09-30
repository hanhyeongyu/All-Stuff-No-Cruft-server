package all_stuff_no_crudt.service

import all_stuff_no_crudt.model.Platform
import all_stuff_no_crudt.model.Token
import all_stuff_no_crudt.model.TokenExpireStrategy
import all_stuff_no_crudt.repository.TokenRepository
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.model.User
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull


@Service
class TokenService(
    private val tokenRepository: TokenRepository
){

    fun tokens(user: User): List<Token> {
        return tokenRepository.findAllByUserId(user.id!!)
    }

    fun add(token: String, user: User, platform: Platform){
        val fcmToken = Token.create(
            value = token,
            userId = user.id!!,
            platform = platform
        )
        tokenRepository.save(fcmToken)
    }

    fun delete(token: String){
        val fcmToken = tokenRepository.findByValue(token)
            .getOrNull() ?: return
        tokenRepository.delete(fcmToken)
    }

    fun delete(token: Token){
        tokenRepository.delete(token)
    }



    fun removeExpireToken(user: User, strategy: TokenExpireStrategy) {
        val fcmTokens = tokenRepository.findAllByUserId(user.id!!)
        val expireTokens = fcmTokens.filter { token ->
            strategy.isExpire(token)
        }
        tokenRepository.deleteAll(expireTokens)
    }

}