package common

import com.y9san9.kotlogram.KotlogramClient
import com.y9san9.kotlogram.models.TelegramApp

const val TELEGRAM_APP_ID = 1767320
const val TELEGRAM_API_HASH = "25f3b19a9a252022ff419e2136b35dbe"
const val TELEGRAM_DEVICE_MODEL = "BossLike Bot"
const val TELEGRAM_SYSTEM_VERSION = "BossLikeBot"
const val TELEGRAM_APP_VERSION = "1.0"
const val LANG_CODE = "en"

val telegramClient = KotlogramClient(
        TelegramApp(
                TELEGRAM_APP_ID,
                TELEGRAM_API_HASH,
                TELEGRAM_DEVICE_MODEL,
                TELEGRAM_SYSTEM_VERSION,
                TELEGRAM_APP_VERSION,
                LANG_CODE
        )
)