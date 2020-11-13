package ru.bosslike.bot

import ru.bosslike.bot.controllers.settings
import java.util.*

val scanner = Scanner(System.`in`)

suspend fun main() {
    accountStorage.awaitLoading()
    println(startMenuText)

    when (scanner.nextLine().toInt()) {
        botSettingsItemId -> {
            settings()
            main()
        }
        launchBotItemId -> TODO()
        else -> {
            println("Ошибка выберите правильный пункт меню.")
            main()
        }
    }
    accountStorage.awaitSaving()
}