package by.arsy.cleancodestudy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import by.arsy.cleancodestudy.data.repository.UserRepositoryImpl
import by.arsy.cleancodestudy.data.storage.SharedPreferencesUserStorage
import by.arsy.cleancodestudy.domain.repository.UserRepository
import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase
import by.arsy.cleancodestudy.presentation.theme.CleanCodeStudyTheme

const val STUB_USER_ID = 1L

class MainActivity : ComponentActivity() {

    private val sharedPreferencesUserStorage: SharedPreferencesUserStorage by lazy {
        SharedPreferencesUserStorage(context = applicationContext)
    }

    private val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userStorage = sharedPreferencesUserStorage)
    }
    private val updateUserBalanceByIdUseCase by lazy { UpdateUserBalanceByIdUseCase(userRepository) }
    private val getUserUseCase by lazy { GetUserByIdUseCase(userRepository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm: DriveBalanceViewModel = viewModel {
                DriveBalanceViewModel(
                    updateUserBalanceByIdUseCase = updateUserBalanceByIdUseCase,
                    getUserUseCase = getUserUseCase
                )
            }
            val balance = vm.balance.collectAsState().value

            CleanCodeStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DriveBalanceScreen(
                        balance = balance,
                        onUpdateUserBalance = { difference ->
                            vm.updateUserBalance(
                                userId = STUB_USER_ID,
                                difference = difference
                            )
                        },
                        onGetUserBalance = { vm.getUserBalance(userId = STUB_USER_ID) },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}