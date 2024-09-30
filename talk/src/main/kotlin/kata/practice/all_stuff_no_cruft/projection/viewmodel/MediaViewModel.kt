package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Media

data class MediaViewModel(
    val id: Long,
    val name: String?,
    var videoURL: String?
){
    constructor(video: Media): this(
        video.id!!,
        video.name,
        null,
    )
}