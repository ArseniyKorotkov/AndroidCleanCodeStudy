package by.arsy.cleancodestudy.app

import android.app.Application
import by.arsy.cleancodestudy.di.appModule
import by.arsy.cleancodestudy.di.dataModule
import by.arsy.cleancodestudy.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(androidContext = this@App)
            modules(
                modules = listOf(
                    appModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }

}