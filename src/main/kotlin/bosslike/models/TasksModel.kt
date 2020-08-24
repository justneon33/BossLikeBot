package bosslike.models

import com.google.gson.annotations.SerializedName

data class TasksResponseModel(
        val status: Int,
        val success: Boolean,
        val data: TasksDataModel
)

data class TasksDataModel(
        val items: ArrayList<TaskResponseModel>,
        val limit: Int
)

data class TaskResponseModel(
        val id: Int,
        val name: TaskNameModel,
        val image: String,
        val task_type: Int,
        val service_type: Int,
        val price: TaskPriceModel
)

data class TaskNameModel(
        val full: String,
        val action: String,
        val short_action: String,
        @SerializedName("object")
        val obj: String
)

data class TaskPriceModel(
        val description: String,
        val value: Int,
        val text: String
)

data class TasksInitResponseModel(
        val url: String,
        val task_type: Int,
        val service_type: Int,
        val seconds: Int,
        val action: String,
        val user_price: Int,
        val comment: String?,
        val answer: TaskAnswerModel
)

data class TaskAnswerModel(
        val value: Int?,
        val text: String?
)

data class TaskCheckModel(
        val message: String,
        val user_price: Int,
        val user: UserPointModel
)

data class UserPointModel(
        val point: Int
)