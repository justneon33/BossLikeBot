package ru.bosslike.bot

import dev.whyoleg.ktd.Telegram

const val exitCode = 0

const val botSettingsItemId = 1
const val launchBotItemId = 2

const val startMenuText = """
    Что желаете сделать? Напишите цифру для выбора выбрав то что нужно с меню ниже.
    $botSettingsItemId. Настроить бота
    $launchBotItemId. Запустить бота
"""

const val addBossLikeAccountItemId = 1
const val editBossLikeAccountItemId = 2
const val changeTelegramAppIdentifiers = 3

const val botSettingsText = """
    Что желаете настроить?
    $addBossLikeAccountItemId: Добавить аккаунт.
    $editBossLikeAccountItemId: Изменить аккаунт.
    $changeTelegramAppIdentifiers: Изменить данные телеграмм приложения.
    $exitCode: Назад
"""

val telegramClient = Telegram().client()
