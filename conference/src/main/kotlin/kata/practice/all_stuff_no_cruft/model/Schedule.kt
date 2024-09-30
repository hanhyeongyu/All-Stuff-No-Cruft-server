package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Embeddable
import java.time.LocalDateTime


@Embeddable
data class  Schedule(
    val meetingStartAt: LocalDateTime,
    val meetingEndAt: LocalDateTime,
    val registerStartAt: LocalDateTime,
    val registerEndAt: LocalDateTime
){

    companion object{
        fun create(
            meetingStartAt: LocalDateTime,
            meetingEndAt: LocalDateTime,
            registerStartAt: LocalDateTime,
            registerEndAt: LocalDateTime
        ): Schedule{

            if (meetingStartAt.isAfter(meetingEndAt)){
                throw IllegalArgumentException("MeetingPeriod start cannot be after $meetingEndAt")
            }

            if (registerStartAt > registerEndAt){
                throw IllegalArgumentException("RegisterPeriod start cannot be after $registerEndAt")
            }

            if (registerEndAt.isAfter(meetingStartAt)){
                throw IllegalArgumentException("RegisterPeriod end cannot be after $meetingStartAt")
            }

            return Schedule(
                meetingStartAt = meetingStartAt,
                meetingEndAt = meetingEndAt,
                registerStartAt = registerStartAt,
                registerEndAt = registerEndAt
            )
        }
    }


    fun isRegisterPeriod(): Boolean{
        val now = LocalDateTime.now()
        return registerStartAt.isBefore(now) && registerEndAt.isBefore(now)
    }

    fun isMeetingPeriod(): Boolean{
        val now = LocalDateTime.now()
        return meetingStartAt.isBefore(now) && meetingEndAt.isBefore(now)
    }

}