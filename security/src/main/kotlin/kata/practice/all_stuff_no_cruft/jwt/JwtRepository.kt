package kata.practice.all_stuff_no_cruft.jwt


interface JwtRepository{
    fun add(id: String, refreshToken : String, refreshExpiration: Long)
    fun token(id: String): String?
}

