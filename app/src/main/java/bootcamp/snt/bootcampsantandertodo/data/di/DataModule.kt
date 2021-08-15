package bootcamp.snt.bootcampsantandertodo.data.di

import androidx.room.Room
import bootcamp.snt.bootcampsantandertodo.data.local.TodoDatabase
import bootcamp.snt.bootcampsantandertodo.data.network.ApiService
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepository
import bootcamp.snt.bootcampsantandertodo.data.repository.TodoRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    factory { HttpLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
}