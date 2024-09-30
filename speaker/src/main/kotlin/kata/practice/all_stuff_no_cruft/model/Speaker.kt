package kata.practice.all_stuff_no_cruft.model

import jakarta.persistence.*
import kata.practice.all_stuff_no_cruft.BaseEntity

@Entity
class Speaker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true)
    var email: Email,

    @Column(length = 1000)
    var encodedPassword: EncodedPassword,

    var profile: Profile
): BaseEntity() {

    fun updateProfile(profile: Profile) {
        this.profile = profile
    }
}