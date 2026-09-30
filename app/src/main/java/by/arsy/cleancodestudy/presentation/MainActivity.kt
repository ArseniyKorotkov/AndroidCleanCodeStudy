package by.arsy.cleancodestudy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import by.arsy.cleancodestudy.presentation.theme.CleanCodeStudyTheme
import dagger.hilt.android.AndroidEntryPoint

const val STUB_USER_ID = 1L

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: DriveBalanceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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