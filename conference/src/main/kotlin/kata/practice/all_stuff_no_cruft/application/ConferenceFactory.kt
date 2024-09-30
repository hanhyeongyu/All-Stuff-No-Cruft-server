package kata.practice.all_stuff_no_cruft.application

import kata.practice.all_stuff_no_cruft.application.command.AddConference
import kata.practice.all_stuff_no_cruft.model.*
import org.springframework.stereotype.Service


@Service
class ConferenceFactory{

    fun create(addConference: AddConference): Conference{
        val name = addConference.conference.name

        val information = Information(
            addConference.information.content,
            addConference.information.contentType,
        )

        val address = Address(
            addConference.address.city,
            addConference.address.street,
            addConference.address.zipCode,
            addConference.address.coordinate.latitude,
            addConference.address.coordinate.longitude
        )


        val schedule = Schedule.create(
            addConference.schedule.meetingStartAt,
            addConference.schedule.meetingEndAt,
            addConference.schedule.registerStartAt,
            addConference.schedule.registerEndAt
        )


        val capacity = Capacity.create(addConference.conference.maxCapacity)

        val price = Price.won(addConference.conference.amount)


        val conference = Conference(
            name = name,
            information = information,
            address = address,
            schedule = schedule,
            capacity = capacity,
            price = price,
        )
        return conference
    }


}