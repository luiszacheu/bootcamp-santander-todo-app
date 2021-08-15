package bootcamp.snt.bootcampsantandertodo

import android.app.Application
import bootcamp.snt.bootcampsantandertodo.data.di.localDataModule
import bootcamp.snt.bootcampsantandertodo.data.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppTodo : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppTodo)
            modules(localDataModule, networkModule)
        }
    }
}