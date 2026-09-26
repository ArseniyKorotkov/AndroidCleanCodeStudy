package by.arsy.cleancodestudy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import by.arsy.cleancodestudy.data.repository.UserRepositoryImpl
import by.arsy.cleancodestudy.domain.repository.UserRepository
import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase
import by.arsy.cleancodestudy.presentation.theme.CleanCodeStudyTheme

const val STUB_USER_ID = 1L

class MainActivity : ComponentActivity() {

    private val userRepository: UserRepository by lazy { UserRepositoryImpl(context = applicationContext) }
    private val updateUserBalanceByIdUseCase by lazy { UpdateUserBalanceByIdUseCase(userRepository) }
    private val getUserUseCase by lazy { GetUserByIdUseCase(userRepository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanCodeStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DriveBalanceScreen(
                        onUpdateUserBalance = { difference ->
                            updateUserBalanceByIdUseCase.execute(
                                userId = STUB_USER_ID,
                                difference = difference
                            )
                        },
                        onGetUserBalance = { getUserUseCase.execute(userId = STUB_USER_ID).balance },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}