package ru.bosslike.bot.models

import kotlinx.serialization.Serializable

@Serializable
data class BossLikeAccount(
        var apiKey: String,
        var instagramCredentials: InstagramCredentials?,
        var proxyCredentials: ProxyCredentials?
)