package all_stuff_no_crudt.application

import all_stuff_no_crudt.application.command.*
import all_stuff_no_crudt.application.command.SendNotificationToTopic
import all_stuff_no_crudt.application.command.SendNotificationToUser
import all_stuff_no_crudt.firebase.Messaging
import all_stuff_no_crudt.firebase.invalidToken
import all_stuff_no_crudt.model.Token
import all_stuff_no_crudt.model.TokenMonthExpireStrategy
import all_stuff_no_crudt.model.Topic
import all_stuff_no_crudt.service.SubscribeService
import all_stuff_no_crudt.service.TokenService
import kata.practice.all_stuff_no_cruft.common.exception.EntityNotFoundException
import kata.practice.all_stuff_no_cruft.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class NotificationApplication(
    private val tokenService: TokenService,
    private val subscribeService: SubscribeService,
    private val userRepository: UserRepository,
){

    /**
     * Send Notification
     */

    @Transactional
    fun handle(command: SendNotificationToTopic){
        val topic = Topic(command.targetType, command.targetId)

        val message = Messaging.commonMessageBuilder(command.title, command.body)
            .setTopic(topic.value())
            .build()
        Messaging.send(message)
    }

    @Transactional
    fun handle(command: SendNotificationToUser){
        val user = userRepository.findById(command.userId)
            .getOrNull()?: throw EntityNotFoundException()

        val tokens = tokenService.tokens(user)

        val message = Messaging.commonMulticastMessageBuilder(command.title, command.body)
            .addAllTokens(tokens.map(Token::value))
            .build()
        val batchResult = Messaging.send(message)


        batchResult.responses.forEachIndexed { index, sendResponse ->
            if(sendResponse.invalidToken()){
                val token = tokens[index]
                tokenService.delete(token)
            }
        }
    }


    /**
     *  Token
     */


    @Transactional
    fun handle(command: OnNewToken){
        val user = userRepository.findById(command.userId)
            .getOrNull()?: throw EntityNotFoundException()
        tokenService.add(command.token, user, command.platform)
        tokenService.removeExpireToken(user, TokenMonthExpireStrategy(2))
        subscribeService.updateSubscribe(user, command.token)
    }


    @Transactional
    fun handle(command: DeleteToken){
        tokenService.delete(command.token)
    }


    /**
     * Subscribe
     */


    @Transactional
    fun handle(command: Subscribe){
        val user = userRepository.findById(command.userId)
            .getOrNull()?: throw EntityNotFoundException()
        subscribeService.subscribe(command.targetType, command.targetId, user)
    }

    @Transactional
    fun handle(command: UnSubscribe){
        val user = userRepository.findById(command.userId)
            .getOrNull()?: throw EntityNotFoundException()
        subscribeService.unSubscribe(command.targetType, command.targetId, user)
    }

}