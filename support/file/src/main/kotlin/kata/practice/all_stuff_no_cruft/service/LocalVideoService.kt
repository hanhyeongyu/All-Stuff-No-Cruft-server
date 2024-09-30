package kata.practice.all_stuff_no_cruft.service


import jakarta.annotation.PostConstruct
import kata.practice.all_stuff_no_cruft.common.exception.BaseException
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.common.exception.ErrorCode.COMMON_SYSTEM_ERROR
import kata.practice.all_stuff_no_cruft.connector.FileConnector
import kata.practice.all_stuff_no_cruft.model.FileUtils
import kata.practice.all_stuff_no_cruft.model.Video
import kata.practice.all_stuff_no_cruft.repository.VideoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.jvm.optionals.getOrNull

@Service
class LocalVideoService(
    private val fileConnector: FileConnector,

    private val videoRepository: VideoRepository,

    @Value("\${file.videos}")
    val dir: String,

    @Value("\${file.videos.hsl}")
    val hslDir: String
): VideoService{

    private val log = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    private fun init(){
        arrayOf(dir, hslDir).map { Path(it) }
            .forEach {
                Files.createDirectories(it)
            }

        arrayOf(dir, hslDir)
            .map { File(it) }
            .forEach { createIfNotExist(it) }
    }

    override fun save(multipartFile: MultipartFile){
        val fileName = multipartFile.originalFilename
        val contentType = multipartFile.contentType
        val inputStream = multipartFile.inputStream

        val storeName = FileUtils.createName(multipartFile)
        val path = Paths.get(dir, storeName)

        multipartFile.transferTo(File(path.toUri()))

        val video = Video(
            name = fileName,
            contentType = contentType,
            path = path.toString()
        )

        videoRepository.save(video)
    }

    override fun video(id: Long): Video? {
        return videoRepository.findById(id).getOrNull()
    }

    override fun process(id: Long ){
        val video = video(id) ?: throw EntityNotFoundException()

        try{
            val videoPath = Paths.get(video.path)
            val outputPath = Paths.get(hslDir, video.id!!.toString())

            Files.createDirectories(outputPath)
            val ffmpegCmd = java.lang.String.format(
                "ffmpeg -i \"%s\" -c:v libx264 -c:a aac -strict -2 -f hls -hls_time 10 -hls_list_size 0 -hls_segment_filename \"%s/segment_%%3d.ts\"  \"%s/master.m3u8\" ",
                videoPath, outputPath, outputPath
            )
            System.out.println(ffmpegCmd)

            val processBuilder = ProcessBuilder("/bin/bash", "-c", ffmpegCmd)
            processBuilder.inheritIO()
            val process = processBuilder.start()
            val exit = process.waitFor()
            if (exit != 0) {
                throw RuntimeException("video processing failed!!")
            }

        }catch (exception: IOException){
            throw BaseException("Video processing fail!", COMMON_SYSTEM_ERROR)
        }
    }



    private fun createIfNotExist(dir: File){
        if (dir.exists()){
            return
        }
        dir.mkdir()

        log.info("Create dir if not exist: ${dir.path}")
    }
}