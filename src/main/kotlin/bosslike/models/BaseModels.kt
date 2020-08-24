package bosslike.models

open class ResponseModel<T>(
        val status: Int,
        val success: Boolean,
        val data: T
)