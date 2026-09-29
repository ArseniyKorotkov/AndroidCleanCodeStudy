package by.arsy.cleancodestudy.app

import android.app.Application
import by.arsy.cleancodestudy.di.AppComponent
import by.arsy.cleancodestudy.di.AppModule
import by.arsy.cleancodestudy.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }

}