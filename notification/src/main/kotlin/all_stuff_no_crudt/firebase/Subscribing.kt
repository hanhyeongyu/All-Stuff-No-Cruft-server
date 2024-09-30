package all_stuff_no_crudt.firebase

import com.google.firebase.messaging.FirebaseMessaging

object Subscribing {
    fun subscribe(tokens: List<String>, topic: String){
        FirebaseMessaging.getInstance().subscribeToTopic(
            tokens,
            topic
        )
    }

    fun unsubscribe(tokens: List<String>, topic: String){
        FirebaseMessaging.getInstance().unsubscribeFromTopic(
            tokens,
            topic
        )
    }
}