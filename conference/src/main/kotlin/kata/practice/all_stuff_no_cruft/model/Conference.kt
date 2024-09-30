package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.*
import kata.practice.all_stuff_no_cruft.BaseEntity



@Entity
class Conference(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    @Embedded
    var information: Information,

    @Embedded
    var address: Address,

    @Embedded
    var schedule: Schedule,

    @Embedded
    var capacity: Capacity,

    @Embedded
    var price: Price,
): BaseEntity(){

    fun joinMember(
        user: User,
        conferenceFullSpecification: ConferenceFullSpecification,
        joinedMemberSpecification: JoinedMemberSpecification
    ): ConferenceMember{
        if (conferenceFullSpecification.isSatisfiedBy(this)){
            throw ConferenceFullException()
        }
        this.capacity = capacity.join()


        val conferenceMember = ConferenceMember(
            conferenceId = this.id!!,
            userId = user.id!!
        )
        if (joinedMemberSpecification.isSatisfiedBy(conferenceMember)){
            throw JoinedMemberException()
        }

        return conferenceMember
    }

    fun updateInformation(information: Information){
        this.information = information
    }

    fun updateSchedule(schedule: Schedule){
        this.schedule = schedule
    }

    fun updateLocation(address: Address){
        this.address = address
    }

    fun updatePrice(price: Price){
        this.price = price
    }

    fun updateCapacity(capacity: Capacity) {
        this.capacity = capacity
    }
}