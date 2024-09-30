package kata.practice.all_stuff_no_cruft.projection.viewmodel

import kata.practice.all_stuff_no_cruft.model.Schedule
import java.time.LocalDateTime

data class ScheduleViewModel(
    val meetingStartAt: LocalDateTime,
    val meetingEndAt: LocalDateTime,
    val registerStartAt: LocalDateTime,
    val registerEndAt: LocalDateTime
){
    constructor(schedule: Schedule) : this(
        meetingStartAt = schedule.meetingStartAt,
        meetingEndAt = schedule.meetingEndAt,
        registerStartAt = schedule.registerStartAt,
        registerEndAt = schedule.registerEndAt
    )
}