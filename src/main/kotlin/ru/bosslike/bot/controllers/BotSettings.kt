package ru.bosslike.bot.controllers

import ru.bosslike.bot.*
import ru.bosslike.bot.models.BossLikeAccount
import ru.bosslike.bot.models.InstagramCredentials
import ru.bosslike.bot.models.ProxyCredentials

suspend fun settings() {
    println(botSettingsText)

    when (scanner.nextLine().toInt()) {
        addBossLikeAccountItemId -> addBossLikeAccount()
        editBossLikeAccountItemId -> editBossLikeAccounts()
        exitCode -> return main()
    }

    return settings()
}

suspend fun editBossLikeAccounts() {
    if (accounts.isEmpty())
        return println("Не найдено ни одного аккаунта.")
    println("Все добавленные аккаунты:")
    var currentId = 1
    accounts.forEach {
        println("$currentId: ${it.apiKey}")
        currentId++
    }

    println("Введите какой аккаунт нужно изменить (идентефикатор, к примеру: 1,2..):")
    return editBossLikeAccount(scanner.nextLine().toInt() - 1)
}

suspend fun editBossLikeAccount(pos: Int) {
    println("Что изменить? \n" +
            "1. API ключ\n" +
            "2. Инстаграмм аккаунт\n" +
            "3. Прокси\n" +
            "4. Телеграмм\n" +
            "0. Вернуться")
    when (scanner.nextLine().toInt()) {
        1 -> {
            println("Новый API ключ ->")
            accounts = accounts.toMutableList().also {
                it[pos].apiKey = scanner.nextLine()
            }
            accountStorage.awaitSaving()
            return editBossLikeAccount(pos)
        }
        2 -> {
            accounts = accounts.toMutableList().also {
                it[pos].instagramCredentials = addInstagramAccount()
            }
            accountStorage.awaitSaving()
            return editBossLikeAccount(pos)
        }
        3 -> {
            accounts = accounts.toMutableList().also {
                it[pos].proxyCredentials = addProxy()
            }
            accountStorage.awaitSaving()
            return editBossLikeAccount(pos)
        }
        4 -> TODO()
        0 -> return
    }
}

suspend fun addBossLikeAccount(apiKey: String? = null, instagramCredentials: InstagramCredentials? = null, proxyCredentials: ProxyCredentials? = null) {
    if (apiKey == null) {
        println("Введите API ключ от Bot API (https://bosslike.ru/botapi) -> ")
        val apiKeyInput = scanner.nextLine()
        return addBossLikeAccount(apiKey = apiKeyInput)
    }
    if (instagramCredentials == null) {
        println("Желаете авторизовать инстаграмм аккаунт? (Д - да/н - нет)")
        when (scanner.nextLine().trim().toLowerCase()) {
            "д", "да" -> return addBossLikeAccount(apiKey, addInstagramAccount())
            else -> Unit
        }
    }
    if (proxyCredentials == null) {
        println("Добавить прокси? (Да / нет)")
        when (scanner.nextLine().trim().toLowerCase()) {
            "д", "да" -> return addBossLikeAccount(apiKey, instagramCredentials, addProxy())
        }
    }
    accounts = accounts.toMutableList().apply {
        add(BossLikeAccount(apiKey, instagramCredentials, proxyCredentials))
    }
    accountStorage.awaitSaving()
}

fun addInstagramAccount(): InstagramCredentials {
    println(
            "Перед тем как добавить убедитесь что у вас отключена двухфакторная авторизация."
    )
    println("Ваш логин для авторизации -> ")
    val login: String = scanner.nextLine()
    println("А теперь введите ваш пароль -> ")
    val password: String = scanner.nextLine()

    return InstagramCredentials(login, password)
}

fun addProxy(): ProxyCredentials {
    println("Введите логин от прокси -> ")
    val login = scanner.nextLine()
    println("Введите пароль от прокси (если пароля нету, оставьте пустым)")
    val password = scanner.nextLine()
    println("Введите хост -> ")
    val host = scanner.nextLine()
    println("Введите порт -> ")
    val port = scanner.nextLine().toInt()
    return ProxyCredentials(login, if (password.isEmpty()) null else password, host, port)
}

fun authorizeTelegram() {
    TODO()
}
