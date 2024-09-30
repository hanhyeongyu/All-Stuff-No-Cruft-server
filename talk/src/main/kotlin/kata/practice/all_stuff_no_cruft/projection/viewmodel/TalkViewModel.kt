package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Speaker
import kata.practice.all_stuff_no_cruft.model.Talk
import kata.practice.all_stuff_no_cruft.projection.querymodel.TalkWithSpeaker

data class TalkViewModel(
    val id: Long,
    val conferenceId: Long,
    val name: String,
    val description: String,
    val speaker: SpeakerViewModel,
){
    constructor(talk: Talk, speaker: Speaker) : this(
        talk.id!!,
        talk.conferenceId,
        talk.name,
        talk.description,
        SpeakerViewModel(speaker)
    )

    constructor(talkWithSpeaker: TalkWithSpeaker) : this(
        talkWithSpeaker.talk, talkWithSpeaker.speaker
    )
}