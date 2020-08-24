package utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private val gson = Gson()

fun Any.toJson(): String {
    return gson.toJson(this)
}

inline fun <reified T> String.parseJson(): T {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}