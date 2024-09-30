package all_stuff_no_crudt.model


data class Topic(
    val targetType: String,
    val targetId: Long,
){
    fun value(): String{
        return "$targetType:$targetId"
    }
}