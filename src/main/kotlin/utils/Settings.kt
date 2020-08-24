package utils

import java.io.File

private val config = File("settings.cfg").also {
    if(!it.exists()) it.writeText(Settings().toJson())
}

val settings: Settings = config.readText().parseJson()

data class Settings (
        var apiKey: String? = null,
        var instagramLogin: String? = null,
        var instagramPassword: String? = null,
        var vkToken: String? = null,
        var tgTimeout: Long = 180000,
        var vkTimeout: Long = 250000,
        var instagramTimeout: Long = 60000
) {
    fun save () = config.writeText(toJson())
}