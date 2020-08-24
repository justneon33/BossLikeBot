package utils

import common.telegramClient

class TelegramController {

    class AuthDSL {
        var phone: String = ""
        var password: () -> String = { "" }
        var code: () -> String = { "" }
    }

    fun auth(handler: AuthDSL.() -> Unit) {
        if (!telegramClient.isAuthorized) {
            val auth = AuthDSL().apply(handler)
            telegramClient.auth(auth.phone, auth.password, auth.code)
        } else println("Account authorized")
    }


}