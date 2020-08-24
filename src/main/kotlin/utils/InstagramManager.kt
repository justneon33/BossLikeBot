package utils

import me.postaddict.instagram.scraper.Instagram
import me.postaddict.instagram.scraper.cookie.CookieHashSet
import me.postaddict.instagram.scraper.cookie.DefaultCookieJar
import me.postaddict.instagram.scraper.interceptor.ErrorInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class InstagramManager (login: String, password: String) {

    private val loggingInterceptor =  HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder()
    .addNetworkInterceptor(loggingInterceptor)
    .addInterceptor(ErrorInterceptor())
    .cookieJar(DefaultCookieJar(CookieHashSet()))
    .build()

    val instagramClient = Instagram(httpClient)

    init {
        instagramClient.basePage()
        instagramClient.login(login, password)
    }

}