package kata.practice.all_stuff_no_cruft.jwt

import kata.practice.all_stuff_no_cruft.common.exception.BaseException
import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.INVALID_REFRESH_TOKEN
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtValidationException
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service


@Service
internal class JwtServiceImp(
    private val jwtComposer: JwtComposer,
    private val jwtDecoder: JwtDecoder,
    private val tokenRepository: JwtRepository,
): JwtService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun issueToken(id: String, roles: List<String>): IssueToken {
        val compose =  jwtComposer.compose(id, roles)
        tokenRepository.add(id, compose.refreshToken, compose.refreshExpiresIn)
        return compose
    }


    override fun issueToken(refreshToken: String): IssueToken {
        try{
            val jwt = jwtDecoder.decode(refreshToken)
            val id = jwt.subject

            if (validateToken(id, refreshToken)){
                return jwtComposer.compose(refreshToken)
            }else{
                throw BaseException(INVALID_REFRESH_TOKEN)
            }
        }catch (e: JwtValidationException){
            log.error("Issue token ", e)
            throw BaseException(INVALID_REFRESH_TOKEN)
        }
    }


    override fun authentication(token: String): AuthenticationToken {
        val jwtAuthenticationToken = JwtAuthenticationToken(jwtDecoder.decode(token))
        return AuthenticationToken(jwtAuthenticationToken)
    }

    private fun validateToken(id: String, refreshToken: String): Boolean{
        return tokenRepository.token(id) == refreshToken
    }


}