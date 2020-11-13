package ru.bosslike.bot.models

import kotlinx.serialization.Serializable

@Serializable
data class ProxyCredentials(
        var login: String?,
        var password: String?,
        var host: String,
        var port: Int
)