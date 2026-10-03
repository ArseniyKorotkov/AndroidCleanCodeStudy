package by.arsy.cleancodestudy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import by.arsy.cleancodestudy.presentation.theme.CleanCodeStudyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

const val STUB_USER_ID = 1L
private val balanceState = MutableStateFlow(0)

@AndroidEntryPoint
class MainActivity : ComponentActivity(), DriveBalanceView {
    @Inject
    lateinit var presenter: DriveBalancePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindView(this)
        enableEdgeToEdge()
        setContent {
            val currentBalance by balanceState.collectAsState()
            CleanCodeStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DriveBalanceScreen(
                        balance = currentBalance,
                        onUpdateUserBalance = { difference ->
                            presenter.updateUserBalance(
                                userId = STUB_USER_ID,
                                difference = difference
                            )
                        },
                        onGetUserBalance = {
                            presenter.getUserBalance(
                                userId = STUB_USER_ID
                            )
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun showBalance(balance: Int) {
        balanceState.value = balance
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}