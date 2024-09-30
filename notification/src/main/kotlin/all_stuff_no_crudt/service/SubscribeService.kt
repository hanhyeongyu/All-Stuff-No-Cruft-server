package all_stuff_no_crudt.service

import all_stuff_no_crudt.firebase.Subscribing
import all_stuff_no_crudt.model.Subscribe
import all_stuff_no_crudt.model.Token
import all_stuff_no_crudt.model.Topic
import all_stuff_no_crudt.repository.SubscribeRepository
import all_stuff_no_crudt.repository.TokenRepository
import com.google.firebase.messaging.FirebaseMessaging
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.model.User
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull


@Service
class SubscribeService(
    private val subscribeRepository: SubscribeRepository,
    private val tokenRepository: TokenRepository,
){


    fun subscribe(targetType: String, targetId: Long, user: User){
        val subscribe = Subscribe.create(targetType, targetId, user.id!!)
        val topic = subscribe.topic()

        val tokens = tokenRepository.findAllByUserId(user.id!!)
        Subscribing.subscribe(
            tokens.map{ it.value},
            topic.value()
        )
    }

    fun unSubscribe(targetType: String, targetId: Long, user: User){
        val subscribe = subscribeRepository.findByUserIdAndTargetTypeAndTargetId(user.id!!, targetType, targetId)
            .getOrNull() ?: throw EntityNotFoundException()
        val topic = subscribe.topic()

        subscribeRepository.delete(subscribe)

        val tokens = tokenRepository.findAllByUserId(user.id!!)
        Subscribing.unsubscribe(
            tokens.map{ it.value },
            topic.value()
        )
    }

    fun updateSubscribe(user: User, token: String){
        val subscribes = subscribeRepository.findByUserId(user.id!!)
        val topics = subscribes.map(Subscribe::topic)
        topics.forEach { topic ->
            Subscribing.subscribe(
                listOf(token),
                topic.value()
            )
        }
    }


}