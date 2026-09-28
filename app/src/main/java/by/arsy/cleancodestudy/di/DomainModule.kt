package by.arsy.cleancodestudy.di

import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase
import org.koin.dsl.module

val domainModule = module {

    single<UpdateUserBalanceByIdUseCase> {
        UpdateUserBalanceByIdUseCase(userRepository = get())
    }

    single<GetUserByIdUseCase> {
        GetUserByIdUseCase(userRepository = get())
    }

}