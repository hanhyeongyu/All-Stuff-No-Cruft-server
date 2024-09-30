package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kata.practice.all_stuff_no_cruft.BaseEntity


@Entity
class Talk(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?= null,

    val conferenceId: Long,

    val speakerId: Long,

    var name: String,

    var description: String,
): BaseEntity(){

     fun hasAuthority(uploader: Speaker): Boolean{
        return this.speakerId == uploader.id
    }
}
