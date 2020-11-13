package ru.bosslike.bot.models

import kotlinx.serialization.Serializable

@Serializable
data class InstagramCredentials(
        var login: String,
        var password: String
)