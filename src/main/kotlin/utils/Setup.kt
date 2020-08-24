package utils

import bosslike.util.InstaBot
import bosslike.util.TelegramBot
import common.Model
import common.telegramClient
import java.util.*

object Setup {

    private val scanner = Scanner(System.`in`)

    fun start() {
//
//        if (settings.apiKey == null) {
//            print("BossLike api key: ")
//            settings.apiKey = scanner.next()
//        }
//
//        val model = Model(TelegramController())
//
//        model.auth {
//            print("Telegram number: ")
//            phone = scanner.next()
//            password = {
//                println("Password: ")
//                scanner.next()
//            }
//            code = {
//                println("Code: ")
//                scanner.next()
//            }
//        }
//
//        if (settings.vkToken == null) {
//            println("VK Token (оставить пустым если не нужно): ")
//
//            settings.vkToken = scanner.next()
//        }
//
//        if (settings.instagramLogin == null) {
//            println("Авторизовать инстаграм? (Y/N)")
//
//            if (scanner.next() == "Y") {
//                println("Логин: ")
//                settings.instagramLogin = scanner.next()
//                println("Пароль: ")
//                settings.instagramPassword = scanner.next()
//            }
//        }
//
//            settings.save()
//            if (settings.instagramLogin != null)
//                Thread {
//                    InstaBot().start()
//                }.start()
//        Thread {
//            model.startWorking()
//        }.start()

        settings.apply {
            if (apiKey == null) {
                input("Введите свой API ключ (bosslike.ru/botapi)")
                apiKey = scanner.next()
            }
            if (!telegramClient.isAuthorized) {
                input("Желаете авторизовать аккаунт телеграмм? Y/N")
                if (scanner.next().toLowerCase() == "y")
                    Model(TelegramController()).auth {
                        print("Telegram number: ")
                        phone = scanner.next()
                        password = {
                            println("Password: ")
                            scanner.next()
                        }
                        code = {
                            println("Code: ")
                            scanner.next()
                        }
                    }
            }
            if(instagramLogin == null) {
                input("Желаете авторизовать инстаграмм аккаунт? Y/N")
                if(scanner.next().toLowerCase() == "y") {
                    input("Instagram Юзернейм")
                    instagramLogin = scanner.next()
                    input("Пароль пользователя")
                    instagramPassword = scanner.next()
                }
            }
            // сохраняем данные
            settings.save()

            // проверяем авторизован ли телеграмм
            if(telegramClient.isAuthorized)
                Thread {
                    // Телеграмм авторизирован, можно начинать работу
                    TelegramBot().start()
                }.start()

            // проверяем авторизован ли инстаграмм
            if(instagramLogin != null)
                Thread {
                    InstaBot().start()
                }.start()

        }

    }

}