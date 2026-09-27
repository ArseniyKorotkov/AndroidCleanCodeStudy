package by.arsy.cleancodestudy.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

const val DEFAULT_INPUT_VALUE = ""

@Composable
fun DriveBalanceScreen(
    balance: Int,
    onUpdateUserBalance: (Int) -> Unit,
    onGetUserBalance: () -> Unit,
    modifier: Modifier = Modifier
) {
    var inputNumber by rememberSaveable { mutableStateOf(DEFAULT_INPUT_VALUE) }
    Column(modifier = modifier) {

        Text(
            text = balance.toString(),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onGetUserBalance.invoke() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("update balance".uppercase())
        }

        TextField(
            value = inputNumber,
            onValueChange = { input ->
                if (!input.isBlank() && input.all { it.isDigit() }) inputNumber = input
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onUpdateUserBalance.invoke(inputNumber.toIntOrNull() ?: 0)
                inputNumber = DEFAULT_INPUT_VALUE
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("add".uppercase())
        }
    }
}