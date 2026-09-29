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
import by.arsy.cleancodestudy.app.App
import by.arsy.cleancodestudy.presentation.theme.CleanCodeStudyTheme
import javax.inject.Inject

const val STUB_USER_ID = 1L

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var vmFactory: DriveBalanceViewModelFactory
    private lateinit var vm: DriveBalanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(mainActivity = this)
        enableEdgeToEdge()
        setContent {
            vm = viewModel(
                factory = vmFactory
            )
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