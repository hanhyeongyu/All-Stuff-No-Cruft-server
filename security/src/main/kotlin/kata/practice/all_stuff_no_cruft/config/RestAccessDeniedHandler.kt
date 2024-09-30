package kata.practice.all_stuff_no_cruft.config

import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.ACCESS_DENIED
import kata.practice.all_stuff_no_cruft.common.response.ResponseException
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import org.springframework.security.access.AccessDeniedException

@Component
class RestAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        log.error("AccessDenied", accessDeniedException)
        log.error("Request Uri : {}", request?.requestURI)

        val responseException = ResponseException(ACCESS_DENIED)
        val responseBody = objectMapper.writeValueAsString(responseException)

        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        response?.status = HttpStatus.FORBIDDEN.value()
        response?.characterEncoding = "UTF-8"
        response?.writer?.write(responseBody)
    }
}