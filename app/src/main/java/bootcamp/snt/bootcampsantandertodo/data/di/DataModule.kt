package bootcamp.snt.bootcampsantandertodo.data.di

import androidx.room.Room
import bootcamp.snt.bootcampsantandertodo.data.local.TodoDatabase
import bootcamp.snt.bootcampsantandertodo.data.network.ApiService
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepository
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepositoryImpl
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val localDataModule = module {

    single {
        Room.databaseBuilder(androidContext(), TodoDatabase::class.java, "todo_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<TodoDatabase>().todoDao()
    }

    single<TodoRepository> {
        TodoRepositoryImpl(get(), get())
    }
}

private const val BASE_URL = "https://bootcamp-santander-todo.herokuapp.com/"

val networkModule = module {

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        GsonConverterFactory.create(GsonBuilder().create())
    }

    single {
        createService<ApiService>(get(), get())
    }
}

private inline fun <reified T> createService(client: OkHttpClient, gson: GsonConverterFactory): T {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(gson)
        .build()
        .create(T::class.java)
}