@file:Suppress("IllegalIdentifier")

package pl.elpassion.eltc

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TCModelTest {

    val api = mock<TCApi>()
    val model = TCModel(api)
    val observer = TestObserver<AppState>()

    @Before
    fun setup() {
        model.state.subscribe(observer)
    }

    @Test
    fun `Emit NoCredentials at the beginning`() {
        observer.assertValue(NoCredentials)
    }

    @Test
    fun `Display correct error on submitting unknown host`() {
        whenever(api.getBuildList()).thenReturn(Single.error(UnknownHostException))
        model.perform(SubmitCredentials("invalid", "user", "pass"))
        observer.assertLastValue(UnknownHost)
    }

    @Test
    fun `Display invalid credentials error on unauthorized call to teamcity api`() {
        whenever(api.getBuildList()).thenReturn(Single.error(InvalidCredentialsException))
        model.perform(SubmitCredentials("http://teamcity:8111", "user", "wrong_pass"))
        observer.assertLastValue(InvalidCredentials)
    }
}

private fun <T> TestObserver<T>.assertLastValue(value: T) {
    assertEquals(value, values().last())
}