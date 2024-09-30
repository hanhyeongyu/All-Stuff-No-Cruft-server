package kata.practice.all_stuff_no_cruft.model


import jakarta.persistence.*
import kata.practice.all_stuff_no_cruft.BaseEntity

@Entity(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true)
    var email: Email,

    @Column(length = 1000)
    var encodedPassword: EncodedPassword,
): BaseEntity()