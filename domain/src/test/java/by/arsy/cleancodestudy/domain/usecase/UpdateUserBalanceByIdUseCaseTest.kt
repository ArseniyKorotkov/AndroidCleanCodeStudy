package by.arsy.cleancodestudy.domain.usecase

import by.arsy.cleancodestudy.domain.model.User
import by.arsy.cleancodestudy.domain.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.never
import org.mockito.kotlin.whenever

class UpdateUserBalanceByIdUseCaseTest {

    var userRepository: UserRepository = mock()

    @AfterEach
    fun tearDown() {
        Mockito.reset(/* ...mocks = */ userRepository)
    }

    @ParameterizedTest
    @ValueSource(ints = [-48, 0, 12])
    fun `should update data if difference is not zero`(testBalanceDifference: Int) {
        val testUserId = 1L
        val testBalance = 4
        val expectedBalance = testBalance + testBalanceDifference
        val testUser = User(testUserId, testBalance)

        val useCase = UpdateUserBalanceByIdUseCase(userRepository = userRepository)

        whenever(
            methodCall = userRepository.getUserById(userId = any())
        ).thenReturn(/* value = */ testUser)

        whenever(
            methodCall = userRepository.updateUserBalanceById(
                userId = testUserId,
                difference = testBalanceDifference
            )
        ).thenReturn(/* value = */ expectedBalance)

        val actualBalance = useCase.execute(
            userId = testUserId,
            difference = testBalanceDifference
        )

        Assertions.assertEquals(
            /* expected = */ expectedBalance,
            /* actual = */ actualBalance
        )

        val isDifferenceZero = testBalanceDifference == 0
        val once = times( /* wantedNumberOfInvocations = */ 1)
        verify(
            /* mock = */ userRepository,
            /* mode = */ if (isDifferenceZero) once else never()
        ).getUserById(userId = testUserId)

        verify(
            /* mock = */ userRepository,
            /* mode = */ if (isDifferenceZero) never() else once
        ).updateUserBalanceById(
            userId = any(),
            difference = eq(value = testBalanceDifference)
        )
    }
}