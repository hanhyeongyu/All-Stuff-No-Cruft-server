package kata.practice.all_stuff_no_cruft.application

import kata.practice.all_stuff_no_cruft.application.command.*
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.model.*
import kata.practice.all_stuff_no_cruft.repository.ConferenceMemberRepository
import kata.practice.all_stuff_no_cruft.repository.ConferenceRepository
import kata.practice.all_stuff_no_cruft.repository.UserRepository
import kata.practice.all_stuff_no_cruft.service.PosterService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull


@Service
class ConferenceApplication(
    private val conferenceRepository: ConferenceRepository,
    private val conferenceMemberRepository: ConferenceMemberRepository,
    private val userRepository: UserRepository,
    private val conferenceFactory: ConferenceFactory,
    private val posterService: PosterService
){

    @Transactional
    fun handle(command: AddConference){
        val conference = conferenceFactory.create(command)
        conferenceRepository.save(conference)
    }

    @Transactional
    fun handle(command: JoinConference){
        val conference = conferenceRepository.findById(command.conferenceId)
            .getOrNull() ?: throw EntityNotFoundException()

        val user = userRepository.findById(command.userId)
            .getOrNull() ?: throw EntityNotFoundException()

        val conferenceFullSpecification = ConferenceFullSpecification()
        val joinedMemberSpecification = JoinedMemberSpecification(conferenceMemberRepository)

        val conferenceMember = conference.joinMember(user, conferenceFullSpecification, joinedMemberSpecification)
        conferenceRepository.save(conference)
        conferenceMemberRepository.save(conferenceMember)
    }

    @Transactional
    fun handle(command: UpdateInformation) {
        val conference = conferenceRepository.findById(command.conferenceId)
            .getOrNull() ?: throw EntityNotFoundException()

        val information = Information(command.content, command.contentType)
        conference.updateInformation(information)
        conferenceRepository.save(conference)
    }

    @Transactional
    fun handle(command: UpdateLocation) {
        val conference = conferenceRepository.findById(command.conferenceId)
            .getOrNull() ?: throw EntityNotFoundException()


        val address = Address(
            city = command.city,
            street = command.street,
            zipCode = command.zipCode,
            latitude = command.latitude,
            longitude = command.longitude
        )

        conference.updateLocation(address)
        conferenceRepository.save(conference)
    }


    @Transactional
    fun handle(command: UpdatePrice){
        val conference = conferenceRepository.findById(command.conferenceId)
            .getOrNull() ?: throw EntityNotFoundException()

        val price = Price.won(command.amount)
        conference.updatePrice(price)
        conferenceRepository.save(conference)
    }

    @Transactional
    fun handle(command: UpdateCapacity){
        val conference = conferenceRepository.findById(command.conferenceId)
            .getOrNull() ?: throw EntityNotFoundException()
        val capacity = conference.capacity.updateMaxCapacity(command.maxCapacity)
        conference.updateCapacity(capacity)
        conferenceRepository.save(conference)
    }

    @Transactional
    fun handle(command: UpdatePoster){
        val conference = conferenceRepository.findById(command.conferenceId)
            .getOrNull() ?: throw EntityNotFoundException()

        val conferenceId = conference.id!!
        val image = command.image

        val poster = posterService.poster(conferenceId)

        if (poster != null){
            posterService.delete(conferenceId)
            posterService.save(conferenceId, image)
        }else{
            posterService.save(conferenceId, image)
        }
    }

}