package by.arsy.cleancodestudy.presentation


import by.arsy.cleancodestudy.domain.model.User
import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DriveBalanceViewModelTest {

    val getUserByIdUseCase: GetUserByIdUseCase = mock()
    val updateUserBalanceByIdUseCase: UpdateUserBalanceByIdUseCase = mock()

    lateinit var viewModel: DriveBalanceViewModel

    @BeforeEach
    fun beforeEach() {
        viewModel = DriveBalanceViewModel(
            updateUserBalanceByIdUseCase = updateUserBalanceByIdUseCase,
            getUserUseCase = getUserByIdUseCase
        )
    }

    @AfterEach
    fun afterEach() {
        Mockito.reset(/* ...mocks = */
            getUserByIdUseCase,
            updateUserBalanceByIdUseCase
        )
    }

    @Test
    fun `should update user balance state`() {
        val testUserId = 1L
        val testUserBalance = 10
        val testUser = User(
            id = testUserId,
            balance = testUserBalance
        )

        whenever(
            methodCall = getUserByIdUseCase.execute(userId = testUserId)
        ).thenReturn(/* value = */ testUser)

        viewModel.send(event = DriveBalanceEvent.GetEvent(userId = testUserId))

        Assertions.assertEquals(
            /* expected = */ testUserBalance,
            /* actual = */ viewModel.state.value.balance
        )

        verify(
            mock = getUserByIdUseCase,
            mode = times(numInvocations = 1)
        ).execute(testUserId)
    }

    @Test
    fun `should call updateUserBalanceByIdUseCase`() {
        val testUserId = 1L
        val testUserDifference = 15

        viewModel.send(
            event = DriveBalanceEvent.UpdateEvent(
                userId = testUserId,
                difference = testUserDifference
            )
        )

        verify(
            mock = updateUserBalanceByIdUseCase,
            mode = times(numInvocations = 1)
        ).execute(testUserId, testUserDifference)
    }

}