package common

import bosslike.util.TelegramBot
import com.github.badoualy.telegram.tl.exception.RpcErrorException
import utils.TelegramController
import java.lang.Exception

class Model(private val telegramController: TelegramController) {
    fun auth(handler: TelegramController.AuthDSL.() -> Unit) = telegramController.auth(handler)
    fun startWorking(): Unit =
        TelegramBot().start()

}