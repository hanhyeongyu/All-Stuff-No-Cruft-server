package kata.practice.all_stuff_no_cruft.projection.querymodel

import kata.practice.all_stuff_no_cruft.model.Speaker
import kata.practice.all_stuff_no_cruft.model.Talk

data class TalkWithSpeaker(
    val talk: Talk,
    val speaker: Speaker
)