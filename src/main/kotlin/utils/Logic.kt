package utils

import bosslike.api.tasksApi
import bosslike.models.TaskResponseModel
import com.y9san9.kotlogram.models.entity.Channel
import common.telegramClient

fun getTelegramTasks (): ArrayList<TaskResponseModel> {
    return tasksApi.getTasks(8, 3, settings.apiKey!!).execute().body()?.data?.items
            ?: throw RequestError("error while trying to get tasks.")
}

fun getTaskUrl (taskId: Int): String {
    info("Initializing job number $taskId")
    val task = tasksApi.initTask(taskId, settings.apiKey!!).execute()
    if(!task.isSuccessful)
        throw RequestError("Error while trying to initialize task. Request not successful (code - ${task.code()}")
    if(!task.body()!!.success)
        throw RequestError("Error while trying to initialize task. Task made not success answer." +
                "\n" +
                "Request message - ${task.body()}" +
                "\nError body: ${task.errorBody()}")
    else return task.body()?.data?.url ?: throw RequestError("Error while trying to initialize task. [data] is null." +
            "\n" +
            "Response: ${task.body()}" +
            "\nError body: ${task.errorBody()}")
}

fun checkTask (taskId: Int): Boolean {
    return tasksApi.checkTask(taskId, settings.apiKey!!).execute().body()?.success ?: false
}

fun subscribeTelegram(url: String) {
    val channel = telegramClient.getByUsername(url.replace("https:", "").replace("t.me", "").replace("/", ""))!! as Channel
    telegramClient.join(channel)
}

class RequestError (override val message: String) : Exception(message)