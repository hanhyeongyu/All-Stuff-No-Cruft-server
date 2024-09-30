package kata.practice.all_stuff_no_cruft.application.command

import org.springframework.web.multipart.MultipartFile
import java.security.Principal

data class UpdateProfileImage(
    val principal: Principal,
    val image: MultipartFile
)