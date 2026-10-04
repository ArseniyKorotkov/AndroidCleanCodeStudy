package by.arsy.cleancodestudy.domain.usecase

import by.arsy.cleancodestudy.domain.model.User
import by.arsy.cleancodestudy.domain.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
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

    @Test
    fun `should not update data if difference is zero`() {
        val testBalanceDifference = 0
        val testUserId = 1L
        val testBalance = 10
        val testUser = User(testUserId, testBalance)

        val useCase = UpdateUserBalanceByIdUseCase(userRepository = userRepository)

        whenever(
            methodCall = userRepository.getUserById(userId = testUserId)
        ).thenReturn(/* value = */ testUser)

        val actualBalance = useCase.execute(
            userId = testUserId,
            difference = testBalanceDifference
        )

        Assertions.assertEquals(
            /* expected = */ testBalance,
            /* actual = */ actualBalance
        )

        verify(
            /* mock = */ userRepository,
            /* mode = */ times(1)
        ).getUserById(userId = testUserId)

        verify(
            /* mock = */ userRepository,
            /* mode = */ never()
        ).updateUserBalanceById(
            userId = any(),
            difference = any()
        )

    }

    @Test
    fun `should update data if difference is not zero`() {
        val testBalanceDifference = 10
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

        verify(
            /* mock = */ userRepository,
            /* mode = */ never()
        ).getUserById(userId = testUserId)

        verify(
            /* mock = */ userRepository,
            /* mode = */ times( /* wantedNumberOfInvocations = */ 1)
        ).updateUserBalanceById(
            userId = any(),
            difference = eq(value = testBalanceDifference)
        )
    }
}