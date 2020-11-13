package ru.bosslike.bot

import com.kotlingang.kds.wrapper.KValueStorage
import ru.bosslike.bot.models.BossLikeAccount

val accountStorage = KValueStorage(listOf<BossLikeAccount>()) {
    name("accounts")
}
var accounts by accountStorage
