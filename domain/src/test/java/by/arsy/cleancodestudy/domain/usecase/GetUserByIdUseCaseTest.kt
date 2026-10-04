package by.arsy.cleancodestudy.domain.usecase

import by.arsy.cleancodestudy.domain.model.User
import by.arsy.cleancodestudy.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

class GetUserByIdUseCaseTest {

    private val userRepository: UserRepository = mock()

    @Test
    fun `should get user with right id`() {
        val expectedId = 1L
        val testUser = User(id = expectedId, balance = 0)

        whenever(
            methodCall = userRepository.getUserById(expectedId)
        ).doReturn(t = testUser)

        val getUserByIdUseCase = GetUserByIdUseCase(userRepository = userRepository)
        val actualId = getUserByIdUseCase.execute(userId = expectedId).id
        Assertions.assertEquals(expectedId, actualId)
    }

}