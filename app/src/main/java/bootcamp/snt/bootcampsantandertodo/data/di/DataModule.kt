package bootcamp.snt.bootcampsantandertodo.data.di

import androidx.room.Room
import bootcamp.snt.bootcampsantandertodo.data.local.TodoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(androidContext(), TodoDatabase::class.java, "todo_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<TodoDatabase>().todoDao()
    }
}