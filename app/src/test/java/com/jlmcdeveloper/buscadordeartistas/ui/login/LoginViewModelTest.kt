package com.jlmcdeveloper.buscadordeartistas.ui.login


import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jlmcdeveloper.buscadordeartistas.R
import com.jlmcdeveloper.buscadordeartistas.data.Repository
import com.jlmcdeveloper.buscadordeartistas.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


private const val FIELD_NULL_STRING = "campo null"


class LoginViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK private lateinit var repository: Repository
    @MockK private lateinit var context: Context
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = LoginViewModel(repository, context)
    }



    // campos vazios
    @Test
    fun login_Field_Empty() {
        viewModel.loadingVisibility.getOrAwaitValue().let { assertFalse( it ) }

        every { context.getString(R.string.campNull) } returns FIELD_NULL_STRING

        viewModel.login()

        assertTrue(viewModel.textErrorName.getOrAwaitValue().equals(FIELD_NULL_STRING))
        assertTrue(viewModel.textErrorPassword.getOrAwaitValue().equals(FIELD_NULL_STRING))
    }


    @Test
    fun login_Success() {
        val spyViewModel = spyk(viewModel)
        spyViewModel.loadingVisibility.getOrAwaitValue().let { assertFalse( it ) }

        spyViewModel.editName.value = "name"
        spyViewModel.editPassword.value = "password"

        var success: () -> Unit = { }

        every { repository.getUser(any(), any(), any()) } answers {success = secondArg() }
        every { context.getString(R.string.campNull) } returns FIELD_NULL_STRING
        spyViewModel.login()

        assertNull(spyViewModel.textErrorName.getOrAwaitValue())
        assertNull(spyViewModel.textErrorPassword.getOrAwaitValue())
        assertTrue(spyViewModel.editName.getOrAwaitValue() == "name")
        assertTrue(spyViewModel.editPassword.getOrAwaitValue() == "password")
        spyViewModel.loadingVisibility.getOrAwaitValue().let { assertTrue( it ) }

        spyViewModel.success = { assertTrue(true) }

        success()
        spyViewModel.loadingVisibility.getOrAwaitValue().let { assertFalse( it ) }
    }

    @Test
    fun login_Failure() {
        val spyViewModel = spyk(viewModel)
        spyViewModel.loadingVisibility.getOrAwaitValue().let { assertFalse( it ) }

        spyViewModel.editName.value = "name"
        spyViewModel.editPassword.value = "password"

        var failure: (String) -> Unit = { }

        every { repository.getUser(any(), any(), any()) } answers {failure = thirdArg() }
        every { context.getString(R.string.campNull) } returns FIELD_NULL_STRING
        spyViewModel.login()

        assertNull(spyViewModel.textErrorName.getOrAwaitValue())
        assertNull(spyViewModel.textErrorPassword.getOrAwaitValue())
        assertTrue(spyViewModel.editName.getOrAwaitValue() == "name")
        assertTrue(spyViewModel.editPassword.getOrAwaitValue() == "password")
        spyViewModel.loadingVisibility.getOrAwaitValue().let { assertTrue( it ) }

        spyViewModel.failure = { error ->  assertEquals("User null", error) }

        failure("User null")
        spyViewModel.loadingVisibility.getOrAwaitValue().let { assertFalse( it ) }
    }
}