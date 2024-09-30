package kata.practice.all_stuff_no_cruft.jwt

interface JwtService {
    fun issueToken(id: String, roles: List<String>): IssueToken
    fun issueToken(refreshToken: String): IssueToken
    fun authentication(token: String): AuthenticationToken
}