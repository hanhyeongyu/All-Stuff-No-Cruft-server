package kata.practice.all_stuff_no_cruft.service

import jakarta.annotation.PostConstruct
import kata.practice.all_stuff_no_cruft.connector.FileConnector
import kata.practice.all_stuff_no_cruft.model.FileUtils
import kata.practice.all_stuff_no_cruft.model.ProfileImage
import kata.practice.all_stuff_no_cruft.repository.ProfileImageRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.jvm.optionals.getOrNull

@Service
class LocalProfileImageService(
    @Value("\${file.images.profile}")
    private val dir: String,
    private val fileConnector: FileConnector,
    private val profileImageRepository: ProfileImageRepository
): ProfileImageService {

    @PostConstruct
    private fun init(){
        val path = Path(dir)
        Files.createDirectories(path)
    }

    override fun save(speakerId: Long, image: MultipartFile) {
        val fileName = FileUtils.createName(image)
        val path = Paths.get(dir, fileName)
        fileConnector.save(image, path)

        val profileImage = ProfileImage(
            userId = speakerId,
            filePath = path.toString()
        )

        profileImageRepository.save(profileImage)
    }

    override fun delete(speakerId: Long): Boolean {
        val profileImage = profileImageRepository.findByUserId(speakerId)
            .getOrNull() ?: return false
        profileImageRepository.delete(profileImage)

        val path = Paths.get(profileImage.filePath)
        return fileConnector.delete(path)
    }

    override fun profileImage(speakerId: Long): ProfileImage? {
        return profileImageRepository.findByUserId(speakerId)
            .getOrNull()
    }

    override fun url(speakerId: Long): String? {
        val profileImage = profileImageRepository.findByUserId(speakerId)
            .getOrNull() ?: return null

        val path = Paths.get(profileImage.filePath)
        val result = fileConnector.url(path)
        return result
    }
}