package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Speaker

data class SpeakerViewModel(
    val id: Long,
    val name: String,
    val introduce: String,
    var imageURL: String?
){
    constructor(speaker: Speaker) : this(
        speaker.id!!,
        speaker.profile.name,
        speaker.profile.introduce,
        null
    )
}