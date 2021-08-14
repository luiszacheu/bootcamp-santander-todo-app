package bootcamp.snt.bootcampsantandertodo

import android.app.Application
import bootcamp.snt.bootcampsantandertodo.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppTodo : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppTodo)
            modules(dataModule)
        }
    }
}