package by.arsy.cleancodestudy.di

import by.arsy.cleancodestudy.presentation.MainActivity
import dagger.Component

@Component(modules = [
    AppModule::class,
    DomainModule::class,
    DataModule::class
])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}