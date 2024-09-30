package kata.practice.all_stuff_no_cruft.service

import jakarta.annotation.PostConstruct
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.connector.FileConnector
import kata.practice.all_stuff_no_cruft.model.FileUtils
import kata.practice.all_stuff_no_cruft.model.Media
import kata.practice.all_stuff_no_cruft.application.VideoProcessor
import kata.practice.all_stuff_no_cruft.repository.MediaRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Paths
import java.util.UUID
import kotlin.jvm.optionals.getOrNull


@Service
class LocalMediaService(
    @Value("\${file.videos.talk}")
    private val dir: String,
    @Value("\${file.videos.hsl.talk}")
    private val processDir: String,
    private val mediaRepository: MediaRepository,
    private val fileConnector: FileConnector,
    private val videoProcessor: VideoProcessor
): MediaService{


    @PostConstruct
    private fun init(){
        fileConnector.createDirectories(dir)
        fileConnector.createDirectories(processDir)
    }

    override fun save(mediaId: UUID, talkId: Long, video: MultipartFile) {
        val fileName = FileUtils.createName(video)
        val path = Paths.get(dir, fileName)
        fileConnector.save(video, path)

        val media = Media(
            mediaId = mediaId,
            talkId = talkId,
            name = video.originalFilename,
            filePath = path.toString()
        )
        mediaRepository.save(media)
    }

    override fun delete(mediaId: UUID): Boolean {
        val talkVideo = mediaRepository.findByMediaId(mediaId)
            .getOrNull() ?: return false
        mediaRepository.delete(talkVideo)

        val path = Paths.get(talkVideo.filePath)
        return fileConnector.delete(path)
    }

    override fun medias(talkId: Long):  List<Media>{
        return mediaRepository.findByTalkId(talkId)
    }

    override fun process(mediaId: UUID) {
        val talkVideo = mediaRepository.findByMediaId(mediaId)
            .getOrNull() ?: throw EntityNotFoundException()

        val videoPath = Paths.get(talkVideo.filePath)
        val outputPath = Paths.get(processDir, talkVideo.mediaId.toString())

        videoProcessor.process(videoPath, outputPath)
    }

    override fun url(mediaId: UUID): String? {
        val talkVideo = mediaRepository.findByMediaId(mediaId)
            .getOrNull() ?: return null

        val path = Paths.get(processDir, talkVideo.mediaId.toString())
        val m3u8Path = Paths.get(processDir, talkVideo.mediaId.toString(), "master.m3u8")
        val result = fileConnector.url(m3u8Path)
        return result
    }
}