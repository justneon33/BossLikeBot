package bosslike.util

import com.github.badoualy.telegram.tl.exception.RpcErrorException
import utils.*
import java.net.ConnectException

class TelegramBot {

    fun start () {
        try {
            work()
        } catch (rpcError: RpcErrorException) {
            debug(rpcError.stackTraceToString())
            info("Rpc error (Таймаут). Ждём ${rpcError.tagInteger} секунд.")
            Thread.sleep(rpcError.tagInteger.toLong())
            start()
        } catch (connError: ConnectException) {
            debug(connError.stackTraceToString())
            info("Ошибка соединения, ждем 30 секунд.")
            Thread.sleep(30000)
            start()
        } catch (e: Exception) {
            debug(e.stackTraceToString())
            info("Что-то пошло не так, ждем 10 секунд.")
            Thread.sleep(10000)
            start()
        }
    }

    fun work () {

        val tasks = getTelegramTasks()

        tasks.filterIndexed { index, taskResponseModel ->
            tasks[index].price.value > taskResponseModel.price.value
        }

        info("Телеграмм задание с индефикатором ${tasks[0].id} выбрано. \n[Прайс] ${tasks[0].price.text} \n[Имя] ${tasks[0].name}\n[Тип сервиса] Telegram")

        val taskUrl = getTaskUrl(tasks[0].id)

        debug("Ссылка на задание: $taskUrl")

        subscribeTelegram(taskUrl)

        if(checkTask(tasks[0].id)) {
            info("Задание завершено успешно, ожидаем 180 секунд..")
            Thread.sleep(180000)
        }
        else {
            info("Задание не было выполнено, ожидаем 15 секунд")
            Thread.sleep(15000)
        }

        start()

    }


}