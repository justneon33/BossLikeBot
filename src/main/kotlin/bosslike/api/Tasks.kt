package bosslike.api

import bosslike.models.ResponseModel
import bosslike.models.TaskCheckModel
import bosslike.models.TasksInitResponseModel
import bosslike.models.TasksResponseModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import utils.settings

import java.util.concurrent.TimeUnit
import javax.net.SocketFactory

val tasksApi = Tasks.retrofit.create(Tasks.TasksAPI::class.java)

object Tasks {

    private const val BASE_URL = "https://api-public.bosslike.ru/v1/bots/tasks/"

    private val gsonConverter: GsonConverterFactory = GsonConverterFactory.create()

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.NONE
    }
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .build()

    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(gsonConverter)
            .build()

    interface TasksAPI {
        @GET("https://api-public.bosslike.ru/v1/bots/tasks/")
        fun getTasks(@Query("service_type") serviceType: Int, @Query("task_type") taskType: Int, @Header("X-Api-Key") apiKey: String): Call<TasksResponseModel>

        @GET("https://api-public.bosslike.ru/v1/bots/tasks/{id}/do")
        fun initTask(@Path("id") id: Int, @Header("X-Api-Key") apiKey: String): Call<ResponseModel<TasksInitResponseModel>>

        @GET("https://api-public.bosslike.ru/v1/bots/tasks/{id}/check")
        fun checkTask(@Path("id") id: Int, @Header("X-Api-Key") apiKey: String): Call<ResponseModel<TaskCheckModel>>

    }

}