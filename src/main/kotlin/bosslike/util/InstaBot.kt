package bosslike.util

import me.postaddict.instagram.scraper.MediaUtil
import ru.bosslike.api.TaskResponseModel
import ru.bosslike.api.Tasks
import utils.*

class InstaBot {

    private val instagramClient = InstagramManager(settings.instagramLogin!!, settings.instagramPassword!!).instagramClient

    fun start () {
        info("Инстаграм бот инициализирован..")
        try {
            work()
        } catch (exception: Exception) {
            exception.printStackTrace()
            info("Ошибка, перезапуск бота.")
            work()
        }
    }

    fun work () {

        info("Получаем задания..")

        Tasks.all(3, 0, settings.apiKey!!) {
            if(success) workWithTask(data!!.items[0])
            else info("Ошибка! Ответ от сервера: ${this.toJson()}")
        }

    }

    fun workWithTask (taskResponseModel: TaskResponseModel) {

        Tasks.initialize(taskResponseModel.id, settings.apiKey!!) {
            if(success) {
                info("Инстаграмм задание инициализировано")
                if(data!!.task_type == 3)
                    instagramClient.followAccount(instagramClient.getAccountByUsername(data!!.url.substringAfterLast("com/").replace("/", "")).id)
                else if(data!!.task_type == 1)
                    instagramClient.likeMediaByCode(MediaUtil.getIdFromCode(data!!.url.substringAfterLast("p/").replace("/", "")))
                else if(data!!.task_type == 4)
                    instagramClient.addMediaComment(data!!.url.substringAfterLast("p/").replace("/", ""), data!!.comment ?: NonSenseGenerator.generate())
                else info("Неизвестный тип задания.")
            } else info("ошибка инициализации задания: $status")
        }

        Thread.sleep(1000)

        Tasks.check(taskResponseModel.id, settings.apiKey!!) {
            info("Задание проверено, ответ: $success")
            Thread.sleep(settings.instagramTimeout)
            work()
        }

    }

}