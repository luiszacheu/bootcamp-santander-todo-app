package bootcamp.snt.bootcampsantandertodo.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://bootcamp-santander-todo.herokuapp.com/"

class NetworkClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun service(): ApiService = retrofit.create(ApiService::class.java)

    private fun client(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}